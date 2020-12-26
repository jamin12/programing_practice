import requests
from bs4 import BeautifulSoup
from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options
import os
import pyautogui
from selenium.webdriver.common.action_chains import ActionChains

#selenium으로 접속하는 방법
def into_selenium(url):
    options = Options()
    #유저 에이전트 설정
    options.add_argument("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36")
    #파일의 폴더 위치 정보
    folderPath = os.path.dirname(os.path.realpath(__file__))
    #익스텐션 정보 입력
    packed_extension_path = folderPath + '/bihmplhobchoageeokmgbdihknkjbknd.crx'
    #구글 옵션에 저장
    options.add_extension(packed_extension_path)
    broswer = webdriver.Chrome(options=options)
    broswer.maximize_window()
    #브라우저 연결
    broswer.get(url)
    return broswer



broswer = into_selenium("https://www.naver.com")
#첫 페이지 핸들 정보 저장
curr_handle = broswer.current_window_handle
#vpn 활성화 
vpn_btn = pyautogui.locateOnScreen("vpn_btn.png")
pyautogui.click(vpn_btn)
pyautogui.move(-170,350,duration=0.25)
pyautogui.sleep(0.5)
pyautogui.click()

#활성화 대기
next_step = pyautogui.locateOnScreen("vpn_active.png")
while next_step is None:
    next_step = pyautogui.locateOnScreen("vpn_active.png",confidence = 0.8) # 정확도 80%

#첫 화면 w3schools로 변경
broswer.get("https://www.w3schools.com/")
broswer.switch_to.window(curr_handle)

#lean_to
elem = broswer.find_element_by_xpath('//*[@id="main"]/div[1]/div[1]/a[1]')
elem.click()
time.sleep(3)

pop_up = pyautogui.locateOnScreen("pop_up.png")
a = 0
while pop_up is None:
    pop_up = pyautogui.locateOnScreen("pop_up.png")
    a = a + 1
    time.sleep(1)
    if a > 5:
        break
if not pop_up is None:
    pyautogui.click(pop_up)
#how_to
elem = broswer.find_element_by_xpath('//*[@id="topnav"]/div/div[1]/a[10]')
elem.click()
time.sleep(3)

#contact from
# elem = broswer.find_element_by_xpath('//*[@id="leftmenuinnerinner"]/a[116]')
# elem = broswer.find_element_by_link_text('Contact Form"')
elem = broswer.find_element_by_xpath('//*[@id="leftmenuinnerinner"]/a[text() = "Contact Form"]')
# elem = broswer.find_element_by_xpath('//*[@id="leftmenuinnerinner"]/a[contains(text(), "Contact")]')#일부 텍스트를 비교

action = ActionChains(broswer)
action.move_to_element(elem)
elem.click()

first_name = "나도"
last_name = "코딩"
country = "Canada"
subject = "퀴즈를 완료하였습니다"

time.sleep(3)
broswer.find_element_by_xpath('//*[@id="fname"]').send_keys(first_name)
broswer.find_element_by_xpath('//*[@id="lname"]').send_keys(last_name)
broswer.find_element_by_xpath('//*[@id="country"]/option[text()="{}"]'.format(country)).click()
broswer.find_element_by_xpath('//*[@id="main"]/div[3]/textarea').send_keys(subject)

time.sleep(5)
broswer.find_element_by_xpath('//*[@id="main"]/div[3]/a').click()


time.sleep(5)
broswer.quit()