# 11.데이터 정렬
import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

print(df.sort_values('키')) # 키 기중으로 오름차순 정렬
print(df.sort_values('키',ascending=False)) # 키 기준으로 내림차순 정렬
print(df.sort_values(['수학','영어'])) # 수학 영어 기준으로 오름차순 정렬(1순위 수학 2순위 영어)
print(df.sort_values(['수학','영어'],ascending=False)) # 수학 영어 기준으로 내림차순 정렬(1순위 수학 2순위 영어)
print(df.sort_values(['수학','영어'],ascending=[True,False])) #수학기준으로 오름차순 영어기준으로 내림차순 정렬 가능(1순위 수학 2순위 영어)
print(df['키'].sort_values()) # 키만 가져와서 정렬
print(df['키'].sort_values(ascending=False)) # 키만가져와서 내림차순 정렬
df.sort_index() # 인덱스 기준으로 정렬
df.sort_index(ascending=False) # 인덱스 기준으로 내림차순 정렬
