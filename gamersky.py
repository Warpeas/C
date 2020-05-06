# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import urllib
import urllib2
import re
import sys
import socket


class Tool:
    removeExtraTag = re.compile(r'_cke_saved_src="http.+?.jpg')

    def replace(self, x):
        x = re.sub(self.removeExtraTag, "", x)
        return x.strip()  # strip()将前后多余内容删除


class Spider:
    def __init__(self, siteURL):
        self.siteURL = siteURL

    def getPage(self, pageIndex):
        if pageIndex == 1:
            url = self.siteURL + ".shtml"
        else:
            url = self.siteURL + "_" + str(pageIndex) + ".shtml"
        print ('准备抓取：' + url)
        request = urllib2.Request(url)
        response = urllib2.urlopen(request)
        # print response.read()
        return response.read()

    def getPic(self, pageIndex):
        page = self.getPage(pageIndex)
        # imgre = re.compile(r'src="(http.+?.jpg)">')适合正常抓取
        imgre = re.compile(r'src="(http.+?.jpg)">')
        imglist = re.findall(imgre, page)
        print (imglist)
        imglist_clr = []
        for imgurl in imglist:
            imgurl_clr = tool.replace(imgurl)
            imglist_clr.append(imgurl_clr.encode('utf-8'))  # 去除u'
        print ('清洗多余字符完成')
        print (imglist_clr)
        x = 0
        for imgurl_clr in imglist_clr:
            print ('正在保存第%s页的第%s张' % (pageIndex, x+1))
            urllib.urlretrieve(imgurl_clr, 'picture_%s_%s.jpg' %
                               (pageIndex, x+1))
            x += 1


socket.setdefaulttimeout(5.0)  # 设置全局超时5秒
tool = Tool()
print ('请输入游民星空网址:')
inURL = raw_input()
inURL = inURL[:-6]  # 去除.shtml
spider = Spider(inURL)
for x in range(1, 20):
    try:
        spider.getPic(x)
    except urllib2.URLError:
        print (URLError)
        print ('已经没有下一页了')
        break
print ('所有图片保存完毕')
