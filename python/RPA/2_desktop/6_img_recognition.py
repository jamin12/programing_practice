import pyautogui
# file_menu = pyautogui.locateOnScreen("file_menu.png")
# # print(file_menu)
# pyautogui.click(file_menu)


# trash_icon = pyautogui.locateOnScreen("trash_icon.png")
# pyautogui.moveTo(trash_icon)

# screen = pyautogui.locateOnScreen("screenshot.png")
# print(screen)

# for i in pyautogui.locateAllOnScreen("checkbox.png"):
#     print(i)
#     pyautogui.click(i)

# trash_icon = pyautogui.locateOnScreen("trash_icon.png")
# pyautogui.moveTo(trash_icon)

#속도 개선
# 1. GrayScale

# trash_icon = pyautogui.locateOnScreen("trash_icon.png",grayscale=True)
# pyautogui.moveTo(trash_icon)

# 2. 범위 지정

# trash_icon = pyautogui.locateOnScreen("trash_icon.png",region=(1718,737,1910,826,1910-1718,826-737))
# pyautogui.moveTo(trash_icon)

# 3. 정확도 조정
# run_btn = pyautogui.locateOnScreen("run_btn.png",confidence=0.9) #90%
# pyautogui.moveTo(run_btn)


# 자동화 대상이 바로 보여지지 않는 경우
file_menu_notepad = pyautogui.locateOnScreen("file_menu_notepad.png")
# if file_menu_notepad:
#     pyautogui.click(file_menu_notepad)
# else:
#     print("not found")
while file_menu_notepad is None:
    file_menu_notepad = pyautogui.locateOnScreen("file_menu_notepad.png")
pyautogui.click(file_menu_notepad)

