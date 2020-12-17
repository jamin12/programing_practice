import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options

#다운로드 경로 변경
chrome_options = Options()
chrome_options.add_experimental_option('prefs',{'download.default_drectory':r'C:\Users\temp\tan\programing_practice'})

#selenium으로 접속하는 방법
def into_selenium(url):
    #브라우져 옵션 설정
    # options = webdriver.ChromeOptions()
    #화면 안뜨게
    # options.headless = True
    #유저 에이전트 설정
    # options.add_argument("user agent")
    #화면 사이즈 설정
    # options.add_argument("window-size=1920x1080")
    broswer = webdriver.Chrome(options = chrome_options)
    broswer.maximize_window()
    #브라우저 연결
    broswer.get(url)
    return broswer

if __name__ == '__main__':
    broswer = into_selenium("https://taegon.kim/archives/5178")
    
    #다운로드 링크 클릭
    elem = broswer.find_element_by_xpath('//*[@id="post-5178"]/div/div[1]/ul/li[1]/a')
    elem.click()

    time.sleep(2)
    broswer.quit()
