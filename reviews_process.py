import pandas as pd
import matplotlib
import re
from textblob import TextBlob
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from textblob import Word
import nltk
import stopwords
import numpy as np

# motion analysis
def motion(data):
    bloblist = list()
    strings = []
    for i, row in data.iterrows():
        strings.extend([row[13]])
    for string in strings:
        if (isinstance(string, str)):
            blob = TextBlob(string)
            # blob.correct()#Grammar correction
            bloblist.append(
                (string, blob.sentiment.polarity, blob.sentiment.subjectivity))
    df = pd.DataFrame(bloblist, columns=['review_body','polarity', 'subjectivity'])
    df.plot()
    df['positive'] = np.where(df.polarity > .5, 1, 0)
    df = pd.merge(data,df,how='inner',on='review_body',left_index=False,right_index=False,sort=True,copy=True,indicator=False)
    df.to_csv('pacifier_motion_analysis.csv')

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
    ranking = pd.DataFrame(data, columns=['rank','term'])
    ranking = ranking[ranking['rank']>=0]
    ranking['rank']=ranking['rank'].astype(int)
    ranking.to_csv('hair_dryer_tf-idf_analysis.csv')


def loadData():
    # Open the tsv
    train = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8')
    data = train[(train["verified_purchase"].isin(["Y", "y"]) & train["vine"].isin(["Y", "y"])) | (train["verified_purchase"].isin(
        ["Y", "y"]) & train["vine"].isin(["N", "n"])) | (train["verified_purchase"].isin(["N", "n"]) & train["vine"].isin(["Y", "y"]))]
    data = data.dropna(axis=0, how='any')
    motion(data)
    tf_idf(data)

if __name__ == "__main__":
    loadData()
