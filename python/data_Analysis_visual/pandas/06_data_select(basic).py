# 데이서 선택(기본)
import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

## 컴럼 선택(lable)
# print(df['이름'])
# print(df[['이름','키']])
df.columns[0]
df.columns[1]
df[df.columns[0]] # df['이름']
df[df.columns[-1]] # df[마지막 값]

#슬라이싱
df['영어'][0:5] # 0~4 까지 영어 점수 데이터 가져옴
df[['이름','키']][0:3] # 처음 3명의 이름 키 정보를 가져옴
df[3:]
