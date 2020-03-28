import pandas as pd
import matplotlib.pyplot as plt
import re
from textblob import TextBlob
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from textblob import Word
import nltk
import stopwords
import numpy as np
import time

# motion analysis


def motion(data):
    bloblist = list()
    strings = []
    for i, row in data.iterrows():
        strings.extend([row[13]])
    for string in strings:
        if (isinstance(string, str)):
            blob = TextBlob(string)
            #blob.correct()#Grammar correction
            bloblist.append(
                (string, blob.sentiment.polarity, blob.sentiment.subjectivity))
    df = pd.DataFrame(bloblist, columns=[
                      'review_body', 'polarity', 'subjectivity'])
    df.plot()
    plt.show()
    df['positive'] = np.where(df.polarity > .5, 1, 0)
    df = pd.merge(data, df, how='inner', on='review_body', left_index=False,
                  right_index=False, sort=True, copy=True, indicator=False)
    df.to_csv('hair_dryer_motion_analysis.csv')

# tf-idf calculation


def tf_idf(corpus):
    vector = TfidfVectorizer(stop_words=stopwords.get_stopwords('english'))
    tf_data = vector.fit_transform(corpus["review_body"].values.astype('U'))
    terms = vector.get_feature_names()
    # sum tfidf frequency of each term through documents
    sums = tf_data.sum(axis=0)
    # connecting term to its sums frequency
    data = []
    for col, term in enumerate(terms):
        data.append((sums[0, col], term))
    ranking = pd.DataFrame(data, columns=['rank', 'term'])
    ranking = ranking[ranking['rank'] >= 0]
    ranking['rank'] = ranking['rank'].astype(int)
    ranking.to_csv('hair_dryer_tf-idf_analysis.csv')


def search_product_id(id, data):
    cnt = 0
    for i, row in data.iterrows():
        # print(row)
        if row[3] == id:
            cnt += 1
    print(cnt)

def search_product_id_mul(id, data):
    cnt = 0
    for i, row in data.iterrows():
        # print(row)
        if row[1] == id:
            cnt += 1
    print(cnt)


def format_lower(data):
    data['marketplace'] = data['marketplace'].str.lower()


def percentage_count(data):
    rows = data['product_id'].value_counts()
    # print(rows)
    percentage = pd.DataFrame()
    group = data.groupby('product_id')['star_rating'].apply(
        sum).reset_index().sort_values('star_rating')
    for i in rows.index:
        # print(i)
        # print(rows[i])
        total = rows[i] * 5
        cnt = group[group['product_id'] == i].sum(axis=1)
        print("%s %d" % (i, 100 * cnt / total))


def vine_percentage(data):
    cnt = 0
    for i, row in data.iterrows():
        if row['vine'] == 'Y':
            cnt += 1
    print("%.4s%%" % (100*cnt/data['vine'].count()))


def filter(train):
    data = train[(train["verified_purchase"].isin(["Y", "y"]))]
    print(data)


def returned(data):
    group = data.groupby('customer_id')['review_id'].count()
    cnt = 0
    for row in group:
        if row > 1:
            cnt += 1
    print(cnt)

def loadData():
    # Open the tsv
    startTime = time.time()
    train = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8')
    # product = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8', usecols=['product_id', 'product_parent', 'product_title', 'product_category'])
    # product = product.drop_duplicates(['product_id'])
    # review = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8', usecols=['marketplace', 'customer_id', 'review_id', 'star_rating', 'helpful_votes', 'total_votes', 'vine', 'verified_purchase', 'review_headline', 'review_body', 'review_date'])
    # mul = review = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8', usecols=['product_id', 'review_id'])
    data = train[(train["verified_purchase"].isin(["Y", "y"]) & train["vine"].isin(["Y", "y"])) | (train["verified_purchase"].isin(
        ["Y", "y"]) & train["vine"].isin(["N", "n"])) | (train["verified_purchase"].isin(["N", "n"]) & train["vine"].isin(["Y", "y"]))]
    data = data.dropna(axis=0, how='any')
    endTime = time.time()
    Time = endTime - startTime
    # print(product)
    # print("import and format success: %d rows in %.8s ms" % (product.shape[0], 1000*Time))
    print("import success: %d rows in %.8s ms" % (train.shape[0], 1000*Time))

    # startTime = time.time()
    # search_product_id("B000FS1W4U", train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Count finish, use %.8s ms" % (1000 * Time))

    # startTime = time.time()
    # search_product_id_mul("B000FS1W4U", mul)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Count finish, use %.8s ms" % (1000*Time))

    # startTime = time.time()
    # format_lower(train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Format finish, use %.8s ms" % (1000 * Time))

    # startTime = time.time()
    # percentage_count(train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Count finish, use %.8s ms" % (1000*Time))

    # startTime = time.time()
    # vine_percentage(train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Calculate finish, use %.8s ms" % (1000 * Time))

    # startTime = time.time()
    # filter(train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Filter finish, use %.8s ms" % (1000 * Time))

    # startTime = time.time()
    # returned(train)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("Count finish, use %.8s ms" % (1000 * Time))

    # startTime = time.time()
    # motion(data)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("motion analysis finish in %.8s ms" % (1000*Time))
    
    # tf_idf(data)


if __name__ == "__main__":
    loadData()
