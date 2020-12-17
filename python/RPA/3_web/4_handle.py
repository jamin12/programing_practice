
import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options
import os
import pyautogui

# selenium으로 접속하는 방법
def into_selenium(url):
    #브라우져 옵션 설정
    # options = webdriver.ChromeOptions()
    #화면 안뜨게
    # options.headless = True
    options = Options()
    #유저 에이전트 설정
    options.add_argument("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
    folderPath = os.path.dirname(os.path.realpath(__file__))
    packed_extension_path = folderPath + '/bihmplhobchoageeokmgbdihknkjbknd.crx'
    options.add_extension(packed_extension_path)
    broswer = webdriver.Chrome(options=options)
    broswer.maximize_window()
    #브라우저 연결
    broswer.get(url)
    return broswer


broswer = into_selenium("https://search.naver.com/search.naver?ie=UTF-8&sm=whl_hty&query=%ED%9A%A8%EC%84%B1%EC%B2%A8%EB%8B%A8%EC%86%8C%EC%9E%AC")
broswer.find_element_by_xpath('//*[@id="web_1"]/div/div[1]/div[2]/a').click()
curr_handle = broswer.current_window_handle
handles = broswer.window_handles #모든 핸들 정보
for handle in handles:
    print(handle) #각 핸들 정보
    broswer.switch_to.window(handle) #각 핸들로 이동
    print(broswer.title)

#새로 이동된 브라우저에서 뭔가 자동화 작업을 수행후 그 브라우저를 종료
print("현재 핸들 닫기")
broswer.close()

#이전 핸들로 돌아오기
print("처음 핸들로 돌아오기")
broswer.switch_to.window(curr_handle)

time.sleep(100000)
broswer.quit()



