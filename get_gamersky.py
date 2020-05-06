# -*- coding: utf-8 -*-
# @File  : HtmlParser.py
# @Author: 赵路仓
# @Date  : 2020/2/28
# @Desc  : 爬取游民星空网站每周精选壁纸
# @Contact : 398333404@qq.com 

import requests
from bs4 import BeautifulSoup
import os
import re

# 网址
url = "http://so.gamersky.com/all/news?s=%u58c1%u7eb8%u7cbe%u9009&type=hot&sort=des&p="
# 请求头
head = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36'
}


# 检查是否存在filePath路径的文件夹，若无则创建，若有则不执行
def createFile(filePath):
    if os.path.exists(filePath):
        print('%s:存在' % filePath)
    else:
        try:
            os.mkdir(filePath)
            print('新建文件夹：%s' % filePath)
        except:
            print("创建文件夹失败！")


# 获取每周壁纸的主题超链接
def href(url):
    try:
        path = "D:/img"
        createFile(path)
        # 清空html_href.txt的内容
        f_init = open(path + '/html_href.txt', 'w', encoding='utf-8')
        f_init.write("")
        f_init.close()
        f = open(path + '/html_href.txt', 'a+', encoding='utf-8')
        for i in range(1, 12):
            r = requests.get(url + str(i))
            r.encoding = r.apparent_encoding
            soup = BeautifulSoup(r.text, 'html.parser')
            hrefs = soup.find_all("div", {"class": "link"})
            for h in hrefs:
                print(h.string)
                # 写入txt文件
                f.write(h.string + '\n')
        f.close()
        print("爬取成功！")
    except:
        print("爬取壁纸主题失败！")


# 读取html_href（主题地址超链接）并写入img_hef（图片地址）
def read():
    try:
        path = "D:/img"
        f_read = open(path + '/html_href.txt', 'r+', encoding='utf-8')
        # 清空img_href.txt的内容
        f_init = open(path + '/img_href.txt', 'w', encoding='utf-8')
        f_init.write("")
        f_init.close()
        # 读取txt文件内容
        f_writer = open(path + '/img_href.txt', 'a+', encoding='utf-8')
        number=1
        for line in f_read:
            try:
                line = line.rstrip("\n")
                r = requests.get(line, headers=head, timeout=3)
                soup = BeautifulSoup(r.text, 'html.parser')
                imgs = soup.find_all("p", {"align": "center"})
                try:
                    for i in imgs:
                        print(re.sub(r'http.*shtml.', '', i.find("a").attrs['href'])+" 当前第"+str(number)+"张图片！")
                        f_writer.write(re.sub(r'http.*shtml.', '', i.find("a").attrs['href']) + '\n')
                        number+=1
                except:
                    print("图片地址出错！")
            except:
                print("超链接出错！")
        f_read.close()
        f_writer.close()
        print("共有"+str(number)+"个图片地址！")
    except:
        print("读取html_href并写入img_href过程失败！！")


def save_img():
        path = "D:/img/"
        img_path="D:/img/images/"
        createFile(path)
        f_read = open(path + 'img_href.txt', 'r+', encoding='utf-8')
        number = 1
        for line in f_read:
            try:
                line = line.rstrip("\n")
                # 根据个数顺序重命名名称
                f_write = open(img_path + str(number) + '.jpg', 'wb')
                r = requests.get(line)
                # 打印状态码
                print(r.status_code)
                # 如果图片地址有效则下载图片状态码200，否则跳过。
                if r.status_code == 200:
                    f_write.write(r.content)
                    # 若保存成功，则命名顺序+1
                    number += 1
                    print("当前保存第" + str(number) + "张图片。")
                f_write.close()
            except:
                print("下载图片出错！！")
        f_read.close()



if __name__ == "__main__":
    href(url)
    read()
    save_img()
# 测试下载图片↓
# save_img("https://img1.gamersky.com/image2019/04/20190427_ljt_red_220_3/gamersky_001origin_001_201942716489B7.jpg","D:/img/1.jpg")