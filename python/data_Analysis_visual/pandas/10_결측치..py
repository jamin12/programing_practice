# 결측치(비어있는 데이터)

import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

## 데이터 채우기 fillna
df.fillna('') # nan 데이터를 빈칸으로 채움
df.fillna('없음') # nan 데이터를 빈칸으로 채움
df.fillna('없음',inplace=True) # nan 데이터를 빈칸으로 채움

df['SW특기'].fillna('확인중',inplace= True) # SW특기 데이터 중에서 nan에 대에서 채움

df.dropna() # 전체 데이터 중에서 nan 을 포함하는 데이터 삭제

# axis: index or columns 행을 지울지 열을 지울지 결정
# how : any or all 하나라도 nan일떄 지우거나 전부 nan일떄 지우거나
df.dropna(axis='index',how='any') #nan가 하나라도 있는 row 삭제
df.dropna(axis='columns',how='any') #nan가 하나라도 있는 columns 삭제
df.dropna(axis='columns',how='all') #데이터 전체가  NaN인 경우에만 Column 삭제