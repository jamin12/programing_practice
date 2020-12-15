import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time
#requests로 접속하는 방법
def into_request(url):
    #유저 에이전트
    headers = {'User-Agent':"user agent"}
    # 사이트 url에 접속 후 Beautifulsoup 객체에 lxml로 저장
    res = requests.get(url,headers = headers)
    res.raise_for_status() # 위에 코드가 이상이 있을 경우 아래 코드 실행 안됨
    soup = BeautifulSoup(res.text,"lxml")
    return soup

#selenium으로 접속하는 방법
def into_selenium(url):
    #브라우져 옵션 설정
    options = webdriver.ChromeOptions()
    #화면 안뜨게
    # options.headless = True
    #유저 에이전트 설정
    options.add_argument("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
    #화면 사이즈 설정
    options.add_argument("window-size=1920x1080")
    broswer = webdriver.Chrome(options = options)
    broswer.maximize_window()
    #브라우저 연결
    broswer.get(url)
    return broswer

if __name__ == '__main__':
    browser = into_selenium("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_input_type_radio")
    browser.switch_to.frame('iframeResult') # frame 전환
    elem = browser.find_element_by_xpath('//*[@id="male"]')
    elem.click()
    browser.switch_to.default_content() #상위로 빠져나옴
    time.sleep(5)
    browser.quit()
