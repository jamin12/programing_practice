# 파일 기본
import os
# print(os.getcwd()) #current working directory 현재 작업 공간
# os.chdir("python") #python 으로 작업 공간 이동
# print(os.getcwd())
# os.chdir("..") #부모 폴더로 이동
# print(os.getcwd())
# os.chdir("../..") #조부모 폴더로 이동
# print(os.getcwd())
# os.chdir("C:/")
# print(os.getcwd())

#파일 경로 
# file_path = os.path.join(os.getcwd(),"my_file.txt") #절대 경로 생성
# print(file_path)

# 파일 경로에서 폴더 정보 가져오기
# print(os.path.dirname(r"C:\Users\temp\tan\programing_practice\my_file.txt"))

#파일 정보 가져오기
# import time
# import datetime

# #파일의 생성 날짜
# file_path = r"C:\Users\temp\tan\programing_practice\README.md"
# ctime = os.path.getctime(file_path)
# print(ctime)
# print(datetime.datetime.fromtimestamp(ctime).strftime("%Y-%m-%d %H:%M:%S"))

# #파일의 수정 날짜
# mtime = os.path.getmtime(file_path)
# print(datetime.datetime.fromtimestamp(mtime).strftime("%Y-%m-%d %H:%M:%S"))

# # 파일의 마지막 접근 날짜
# atime = os.path.getatime(file_path)
# print(datetime.datetime.fromtimestamp(atime).strftime("%Y-%m-%d %H:%M:%S"))

# # 파일 크기
# size = os.path.getsize(file_path)
# print(size) #바이트 단위로 파일 크기 가져오기


#파일 목록 가져오기
# print(os.listdir()) #모든 폴더, 파일, 목록 가져오기
# print(os.listdir("python")) # 주어진 폴더 밑에서 모든 폴더, 파일 목록 가져오기

#파일 목록 가져오기 (하위 폴더 모두 포함)
# result = os.walk("python") #주어진 폴더 밑에 있는 모든 폴더, 파일 목록 가져오기
# for root,dirs,files in result:
#     print(root,dirs,files)

#만약 폴더 내에서 특정 파일들을 찾으려면?
# name = "11_file_system.py"
# result = []
# for root,dirs,files in os.walk("."):
#     if name in files:
#         result.append(os.path.join(root,name))

#만약 폴더 내에서 특정 패턴을 가진 파일들을 찾으려면?
# *.xlsx, *txt
# import fnmatch
# pettern = "*.py"
# result = []
# for root,dirs,files in os.walk("."):
#     for name in files:
#         if fnmatch.fnmatch(name,pettern):
#             result.append(os.path.join(root,name))
# print(result)

# 주어진 경로가 피일인지? 폴더인지?
# print(os.path.isdir("python"))
# print(os.path.isfile("python"))

# print(os.path.isdir("README.md"))
# print(os.path.isfile("README.md"))

#만약에 지정된 경로에 해당 파일/폴더가 없다면?
# print(os.path.isdir("READㅁㅁㄴME.md"))


#주어진 경로가 존재하는지?
# if os.path.exists("python"):
#     print("파일이 존재")
# else:
#     print("존재 안함")

# #파일 만들기
# open("new_file.txt","a").close() #빈파일 생성

# 파일명 변경하기
# os.rename("new_file.txt","new_file_rename.txt")#이름 변경

#파일 삭제하기
# os.remove("new_file_rename.txt")

#폴더 만들기
# os.mkdir("new_folder")
# os.makedirs("new_folders/a/b/c") #여러 하위 폴더 생성

#폴더면 변경하기
# os.rename("new_folders", "new_folder_rename")

#폴더 지우기
# os.rmdir("new_folder_rename") #폴더 안이 비었을 때만 삭제 가능

import shutil #shell utilities
# shutil.rmtree("new_folder_rename") #폴더 안이 비어있지 않아도 완전 삭제 가능

#파일 복사하기
# 어떤 파일을 폴더 안으로 복사하기
# shutil.copy("./python/RPA/2_desktop/11_file_system.py","test_folder") #원본 경로, 대상 경로
# shutil.copy("./python/RPA/2_desktop/11_file_system.py","test_folder/copyied_filesystem.py") #원본 경로, 대상 경로 변경된 파일 이름

# shutil.copyfile("./python/RPA/2_desktop/11_file_system.py","test_folder/copyied_filesystem2.py")#원본 파일 경로, 대상 파일 경로와 파일 이름(필수로 해야함)
# shutil.copy2("./python/RPA/2_desktop/11_file_system.py","test_folder/copy2.py")

# copy copyfile 메타정보 복사 x
# copy2 : 메타정보 복사 O

#폴더 복사
# shutil.copytree("test_folder","test_folder2")# 원본 폴더 경로, 대상 폴더 경로
# shutil.copytree("test_folder","test_folder3")

#폴더 이동
shutil.move("test_folder","test_folder2") #폴더가 존재하지 않으면 폴더명이 변경되는 효과를 볼수 있다