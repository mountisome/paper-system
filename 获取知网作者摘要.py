import re

from selenium import webdriver
from selenium.webdriver.common.by import By
import requests
import time
import pandas as pd
import sys

browser = webdriver.Chrome(
    executable_path='C:\\?\\chromedriver.exe')  # 获得驱动，?表示python所在的路径地址
browser.implicitly_wait(10)
browser.get('https://www.cnki.net/')  # 得到网址

key = sys.argv[1]
num = int(sys.argv[2])
if num <= 0:
    print("输入的页数不合法！")
    exit(-1)

browser.find_element(by=By.ID, value='txt_SearchText').send_keys(key + '\n')  # 在搜索框中输入关键字

elements = browser.find_elements(by=By.CLASS_NAME, value='fz14')  # 打开第一页获取定影的元素
time.sleep(1)
urls = []
sub_urls = [element.get_attribute('href') for element in elements]  # 每个元素中提取网址

for page in range(2, num + 1):
    time.sleep(2)
    browser.find_element(by=By.ID, value='page' + str(page)).click()
    elements = browser.find_elements(by=By.CLASS_NAME, value='fz14')
    temp_url = [element.get_attribute('href') for element in elements]
    sub_urls += temp_url

titles = []
authors = []
abstracts = []
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36',
    'Referer': 'https://kns.cnki.net/kns8/defaultresult/index'
}

for url0 in sub_urls:
    response = requests.get(url=url0, headers=headers).text  # 用requests模块获取论文概述内容
    title = re.findall('<h1>(.*?)</h1>', response)  # 获取题目
    if len(title) == 0:
        titles.append('没有找到题目')
    else:
        titles.append(title[0])
    author = re.findall("'au','(.*?)','", response)  # 获取作者
    if len(author) == 0:
        authors.append('没有找到作者')
    else:
        authors.append(author[0])
    abstract = re.findall('</span><span id="ChDivSummary" name="ChDivSummary" class="abstract-text">(.*?)</span>',
                          response)  # 获取摘要
    if len(abstract) == 0:
        abstracts.append('没有找到摘要')
    else:
        abstracts.append(abstract[0])
    time.sleep(1)
    print('Done')

total_data = pd.DataFrame({'网址': sub_urls, '题目': titles, '作者': authors, '摘要': abstracts})
total_data.to_csv('文献信息.csv', encoding='GBK')  # 存数据
