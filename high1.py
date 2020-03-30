import time
import json
import random
from urllib.error import URLError
from urllib import request
import http.client
import requests
import gevent
import gevent.pool

# **请求URL**
url = 'http://127.0.0.1/'
headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1;WOW64; rv:45.0) Gecko/20100101 Firefox/45.0'
}


def make_data(num):
    # """制造请求数据"""
    data = { 
            "id": num,   
            "name": "test",
    }
    return data


def run():
    """三种模拟请求"""
    num = random.randint(100, 999)
    data = make_data(num)
    try:
        # s1:request请求
        req = request.Request(url=url, data=data, headers=headers, method="POST")
        response = request.urlopen(req)
        resp = response.read()
        print("服务器返回值为:\n", resp.decode('utf-8'))
        
        # s2:httpclient请求
        # httpclient = http.client.HTTPConnection(host='127.0.0.1', port=8001)
        # httpclient.request("POST", '/insert', data, headers)
        # response = httpclient.getresponse()
        # print(response.read().decode())

        # s3:requests请求
        # resp = requests.post(url=url, data=data, headers=headers)
        # print("状态:\n", resp)
        # print("请求头:\n", resp.headers)
        # print("服务器返回值为:\n", resp.content.decode())
    except URLError as e:
        print('请求', e)
    except Exception as e:
        print('请求错误：', e)


def call_gevent(count):
    """调用gevent 模拟高并发"""
    begin_time = time.time()
    run_gevent_list = []
    pool = gevent.pool.Pool(50)
    #并发数，即同时有n个请求
    for i in range(count):
        print('--------------%d--Test-------------' % i)
        pool.add(gevent.spawn(run))
        pool.join()
        end = time.time()
        print('单次测试时间（平均）s:', (end - begin_time) / count)
        print('累计测试时间 s:', end - begin_time)

if __name__ == '__main__':
    # 10万并发请求
    test_count = 1
    call_gevent(count=test_count)
# ————————————————
# 版权声明：本文为CSDN博主「梅山学子」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
# 原文链接：https://blog.csdn.net/LeonTom/java/article/details/83578773