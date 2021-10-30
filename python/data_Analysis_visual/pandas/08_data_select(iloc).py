# 08 데이터 선택(iloc)
# 위치를 이용하여 원하는 row에서 원하는 col 선택

import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

df.iloc[0] # 0번쨰 위치의 데이터
df.iloc[4] # 4번쨰 위치의 데이터
df.iloc[0:5] # 0~4번쨰 위치의 데이터

df.iloc[0,1] # 0번째 위치의 1번쨰(학교) 데이터
df.iloc[4,2] # 5번 학생의 키 데이터(4번쨰 위치의 2번쨰(키) 데이터)
df.iloc[[0,1],2] # 0 1번째 위치의 학생의 2번쨰 데이터
df.iloc[[0,1],[3,4]] # 0 1번째 위치의 학생의 3 4번쨰 데이터
df.iloc[0:5,3:8] # 0~4 번째 위치의 학생 중에서, 3~7번째 데이터
