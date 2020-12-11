import pyautogui

#저대 좌표로마우스 이동
# pyautogui.moveTo(200,100) #지정한 위치로 마우스를 이동
# pyautogui.moveTo(100,200,duration=5) #5초 동안 100,200위치로 이동

# pyautogui.moveTo(100,100,duration=0.25)
# pyautogui.moveTo(200,200,duration=0.25)
# pyautogui.moveTo(300,300,duration=0.25)

#상대 좌표로 이동 (현제 커서가 있는 위치로부터)
# pyautogui.moveTo(100,100,duration=0.25)
# print(pyautogui.position())
# pyautogui.move(100,100,duration=0.25)
# print(pyautogui.position())
# pyautogui.move(100,100,duration=0.25)
# print(pyautogui.position())

p = pyautogui.position()
print(p[0], p[1]) # x,y
print(p.x,p.y)