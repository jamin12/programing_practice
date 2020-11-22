from tkinter import *
import tkinter.ttk as ttk
import time

root = Tk()
root.title("Nado GUI") # 타이틀 설정
root.geometry("640x480")

def create_new_file():
    print("새 파일을 만듭니다.")

menu = Menu(root)

# File 메뉴
#tearoff : 메뉴 상단을 눌렀을때 메뉴의 창이 따로 안뜨게 하도록 설정
menu_file = Menu(menu, tearoff = 0)
menu_file.add_command(label = "New File",command = create_new_file)
menu_file.add_command(label = "New Window")
menu_file.add_separator()
menu_file.add_command(label = "Open File...")
menu_file.add_separator()
menu_file.add_command(label = "Save All",state = "disable") # 비활성화
menu_file.add_command(label = "Exit",command = root.quit)

# Edit 메뉴 (빈값)
menu.add_cascade(label = "Edit")

# Language 메뉴 추가(라디오 버튼)
menu_lang = Menu(menu,tearoff = 0)
menu_lang.add_radiobutton(label="python")
menu_lang.add_radiobutton(label="java")
menu_lang.add_radiobutton(label="c++")
menu.add_cascade(label = "Language",menu = menu_lang)

# View 메뉴
menu_view = Menu(menu,tearoff = 0)
menu_view.add_checkbutton(label="show minimap")
menu_view.add_checkbutton(label="hihi")
menu_view.add_checkbutton(label="byby")
menu.add_cascade(label = "View",menu = menu_view)

menu.add_cascade(label = "File", menu = menu_file)
root.config(menu = menu)
root.mainloop()