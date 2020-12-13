import pyautogui
w = pyautogui.getWindowsWithTitle("제목 없음")[0] #메모장 1개 띄운 상태에서 가져옴
w.activate()

# pyautogui.write("12345")
# pyautogui.write("NadoCoding", interval=0.25)
# pyautogui.write("나도코딩") #한글 안됨

# pyautogui.write(["t","e","s","t","left","left","right","l","a","enter"],interval=0.25)
#tset 순서대로 적고 왼쪽 방향키 2번 오른쪽 한번 la 적고 엔터
#automate the boring stuff with python

#특수 문자
# shift 4->$
# pyautogui.keyDown("shift") # 쉬프트키를 누른 상태에서
# pyautogui.press("4") #숫자 4를 입력하고
# pyautogui.keyUp("shift") #쉬프트 키를땐다

#조합키(hot key)
# pyautogui.keyDown("ctrl")
# pyautogui.keyDown("a")
# pyautogui.keyUp("a") #press("a")
# pyautogui.keyUp("ctrl")

#간편한 조합키
# pyautogui.hotkey("ctrl","alt","shift","a")# 순서대로 눌렀다가 역방향으로 땐다
# pyautogui.hotkey("ctrl","a")

import pyperclip
# pyperclip.copy("나도코딩") #나도코딩 글자를 클립보드에 저장
# pyautogui.hotkey("ctrl","v")

def my_write(text):
    pyperclip.copy(text)
    pyautogui.hotkey("ctrl","v")

my_write("나도코딩")

#자동화 프로그램 종료
#win ; ctrl + alt + del 