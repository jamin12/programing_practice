import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time
from selenium.webdriver.common.action_chains import ActionChains

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
    broswer = into_selenium('https://www.w3schools.com/html/')
    time.sleep(5)
    #특정 영역 스크롤
    elem = broswer.find_element_by_xpath('//*[id="leftmenuinnerinner"]/a[61]')

    # 방법 1 ActionChain
    # actions = ActionChains(broswer)
    # actions.move_to_element(elem).perform()

    #방법 2 : 좌표 정보 이용
    xy = elem.location_once_scrolled_into_view #함수 아님
    print("type : ", type(xy))
    print("value : ",xy)
    elem.click()

    time.sleep(5)
    broswer.quit()