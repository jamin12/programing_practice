from tkinter import *
import tkinter.ttk as ttk

root = Tk()
root.title("Nado GUI") # 타이틀 설정
root.geometry("640x480")

values = [str(i) + "일" for i in range(1,32)]
combobox = ttk.Combobox(root,height = 5,values = values)
combobox.pack()
combobox.set("카드 결제일") # 최초 목록 제목 설정


readonly_combobox = ttk.Combobox(root,height = 10,values = values,state = "readonly")
readonly_combobox.pack()
readonly_combobox.current(0) # 최초 목록 제목 설정

def btncmd():
    print(combobox.get()) # 선택된 값 표시
    print(readonly_combobox.get()) # 선택된 값 표시
btn = Button(root,text="선택",command=btncmd)
btn.pack()

root.mainloop()