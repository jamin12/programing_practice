import random # 랜덤모듈 임포트
import pandas as pd
import tkinter

window = tkinter.Tk()
window.resizable(False,False)
text = tkinter.Text(window)

def get_random_number():
    number = random.randint(1, 45)
    return number #  1~45 난수를뽑아 number에 반환하는 함수 생성.

def get_lotto_nums():
    text.delete("1.0","end")
    lotto_nums = []
    for i in range(3):
        lotto_num = [] # 표준출력 오름차순으로 구하려면 리스트 sort사용.
        while True: # 조건3: 중복이 나올 수 있으니 6개 뽑기보단 무한루프로 일단 돌리기
            random_num = get_random_number() # random_num에 반환된 num 저장
            if random_num not in lotto_num: # 그 반환된 num이 리스트에 없다면?
                lotto_num.append(random_num) # 리스트에 넣기!
            if len(lotto_num) == 6: # 리스트 길이가 6이되면 반복문 탈출
                break
        lotto_nums.append(lotto_num)
    for i in lotto_nums:
        text.insert(1.0,str(i)+"\n")


button = tkinter.Button(window, text ="로또번호 만들기" ,command=get_lotto_nums,repeatinterval=10000)

button.pack()
text.pack()


window.mainloop()


# lotto_num.sort() # 오름차순 정렬


# with open('lotto_num.csv', 'a', newline='') as f_object:  
#     # Pass the CSV  file object to the writer() function
#     writer_object = writer(f_object)
#     # Result - a writer object
#     # Pass the data in the list as an argument into the writerow() function
#     writer_object.writerow(lotto_num)  
#     # Close the file object
#     f_object.close()