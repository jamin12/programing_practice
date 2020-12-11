import pyautogui
#스크린 샷 찍기
# img = pyautogui.screenshot()
# img.save("screenshot.png") #파일로 저장

# pyautogui.mouseInfo()
# 972,13 0,140,212 #008CD4

pixel = pyautogui.pixel(972,13)
# print(pixel)

# print(pyautogui.pixelMatchesColor(972,13,(0,140,212)))
# print(pyautogui.pixelMatchesColor(972,13,pixel))
print(pyautogui.pixelMatchesColor(972,13,(0,140,211)))