import psycopg2
import time
import pandas as pd
import matplotlib.pyplot as plt
from textblob import TextBlob
import numpy as np
import gevent
import gevent.pool
<<<<<<< HEAD
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
import stopwords
=======
>>>>>>> bd045a063c82933e5ce83c2fbbfed276184424e0

# motion analysis


def motion(data):
    bloblist = list()
    strings = []
    for row in data:  # for i, row in data.itterrow() for pandas
        strings.extend([row[13]])
    for string in strings:
        if (isinstance(string, str)):
            blob = TextBlob(string)
            # blob.correct()#Grammar correction
            bloblist.append(
                (string, blob.sentiment.polarity, blob.sentiment.subjectivity))
    df = pd.DataFrame(bloblist, columns=[
                      'review_body', 'polarity', 'subjectivity'])
    # df.plot()
    # plt.show()
    df['positive'] = np.where(df.polarity > .5, 1, 0)
    # df = pd.merge(data, df, how='inner', on='review_body', left_index=False,
    #               right_index=False, sort=True, copy=True, indicator=False)
    df.to_csv('hair_dryer_motion_analysis.csv')

<<<<<<< HEAD
# tf-idf calculation


def tf_idf(corpus):
    vector = TfidfVectorizer(stop_words=stopwords.get_stopwords('english'))
    tf_data = vector.fit_transform(c[13] for c in corpus)
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
    ranking.plot()
    plt.show()
    ranking.to_csv('hair_dryer_tf-idf_analysis.csv')

=======
>>>>>>> bd045a063c82933e5ce83c2fbbfed276184424e0

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
        # print("%s %d" % (i, 100 * cnt / total))


def run_file(train):
    try:
        #   insert a row and write to file
        train = train.append({'marketplace':'us', 'customer_id':'820585042', 'review_id':'R2D9SRZTE2SAF2','product_id': 'B007R0D302','product_parent': '614083399', 'product_title':'salon sundry professional bonnet style hood 1,000 watt salon hair dryer - multiple colors available', 'product_category':'Beauty', 'star_rating':5,'helpful_votes': 0,'total_votes': 0,'vine': 'N','verified_purchase': 'Y','review_headline': 'Great product','review_body': 'Wow! Great product. The wind is soft and warm. However it''s a pity that I don''t have hair.','review_date': '1/1/2019'},ignore_index=True)
        train.to_csv('hair_dryer_change.tsv', sep='\t', encoding='utf8',index=False)
        #   delete a row and write to file
        row = train[train['customer_id'] == '820585042']
        delete = list(row.customer_id)
        remain = list(train.customer_id)
        rest = list(set(delete) ^ set(remain))
        train = train[train.customer_id.isin(rest)]
        train.to_csv('hair_dryer_change.tsv', sep='\t',index=False)
        #   search in file
        percentage_count(train)

    except Exception as e:
        print('error: ', e)


def run_sql(cur):
    sql_command1 = "INSERT INTO public.hair_dryer (marketplace, customer_id, review_id, product_id, product_parent, product_title, product_category, star_rating, helpful_votes, total_votes, vine, verified_purchase, review_headline, review_body, review_date) VALUES ('us', 820585042, 'R2D9SRZTE2SAF2', 'B007R0D302', 614083399, 'salon sundry professional bonnet style hood 1,000 watt salon hair dryer - multiple colors available', 'Beauty', 5, 0, 0, 'N', 'Y', 'Great product', 'Wow! Great product. The wind is soft and warm. However it''s a pity that I don''t have hair.', '1/1/2019')"
    sql_command2 = "delete from hair_dryer where customer_id = '820585042';"
    sql_command3 = "with sub as(select product_id, round(100 * cast(sum(star_rating) as float) / (5 * count(*))) as percentage from hair_dryer group by product_id)select product_id, percentage from sub where percentage = (select max(percentage)from sub);"
    try:
        #   sql_command1
        cur.execute(sql_command1)

        #   sql_command2
        cur.execute(sql_command2)

        #   sql_command3
        cur.execute(sql_command3)

    except Exception as e:
        print('error: ', e)


def loadDataFromFile():
    starTime = time.time()
    train = pd.read_csv('hair_dryer.tsv', sep='\t', header=0, encoding='utf8')
    print("Data load successfully")
    endTime = time.time()
    Time = endTime - starTime
    print("success in %.8s ms" % (1000 * Time))
    return train


def loadData():
    starTime = time.time()
    conn = psycopg2.connect(database="reviews", user="hunter",
                            password="@0000819wd", host="localhost", port="5432")
    print("Link database successfully")

    cur = conn.cursor()
    cur.execute("SELECT * from hair_dryer")
<<<<<<< HEAD
    # rows = cur.fetchall()
=======
    rows = cur.fetchall()
>>>>>>> bd045a063c82933e5ce83c2fbbfed276184424e0
    # sql_command = "select * from hair_dryer"
    # try:
    #     data = pd.read_sql(sql_command, conn)
    # except:
    #     print("load data from postgres failure !")
    #     exit()

    # for row in rows:
    #    print(row)
    print("Data load successfully")
    endTime = time.time()
    Time = endTime - starTime
    print("success in %.8s ms" % (1000 * Time))
    # conn.close()

    # startTime = time.time()
    # motion(rows)
    # endTime = time.time()
    # Time = endTime - startTime
    # print("motion analysis finish in %.8s ms" % (1000*Time))

    return cur, conn


def call_gevent(count, cur):
    begin_time = time.time()
    run_gevent_list = []
    pool = gevent.pool.Pool(10)
    for i in range(count):
        print('--------------%dth Test-------------' % (i+1))
        pool.add(gevent.spawn(run_sql(cur)))
        # pool.add(gevent.spawn(run_file(cur)))
        pool.join()
        end = time.time()
        print('Single test time (average): %.2f ms' %
              (1000 * (end - begin_time) / (count+1)))
        print('Total test time: %.2f ms' % (1000 * (end - begin_time)))


if __name__ == "__main__":
    cur, conn = loadData()
    # cur = loadDataFromFile()
<<<<<<< HEAD
    # test_count = 10
    # call_gevent(test_count, cur)

    rows = cur.fetchall()
    startTime = time.time()
    tf_idf(rows)
    endTime = time.time()
    Time = endTime - startTime
    print("tf-idf analysis finish in %.8s ms" % (1000*Time))

=======
    test_count = 10
    call_gevent(test_count, cur)
>>>>>>> bd045a063c82933e5ce83c2fbbfed276184424e0
    conn.close()
