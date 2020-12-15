import pyautogui
import random
import pyperclip

def my_write(text):
    pyperclip.copy(text)
    pyautogui.hotkey("ctrl","v")

# 그림판 활성화
pyautogui.hotkey("win","r")
pyautogui.write("mspaint")
pyautogui.press("enter")

#그림판 최대화
pyautogui.sleep(0.5)#
w = pyautogui.getWindowsWithTitle("제목 없음")[0]
w.activate()
if w.isMaximized == False: #현재 최대화가 되지 않았다면
    w.maximize() # 최대화

#그림판 글자 적기 위한 작업
write_btn = pyautogui.locateOnScreen("wirte.png")
pyautogui.click(write_btn)
x = random.randint(6,302)
y = random.randint(152,443)
pyautogui.moveTo(x,y,duration=0.25)
pyautogui.click()
pyautogui.write("very good",interval=0.1)
my_write("참 잘했어요")

#저장 여부 설정
w.close()
pyautogui.sleep(5)
dontsave = pyautogui.locateOnScreen("dontsave.png")
pyautogui.click(dontsave)
