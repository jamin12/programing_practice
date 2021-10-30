# 5 데이터 확인
import pandas as pd

df = pd.read_excel('score.xlsx',index_col='지원번호')

# 데이터프레임 확인
df.describe() # 계산이 가능한 데이터에 대해 column별로 데이터의 갯수,평균,표준편차 등의 정보를 보여줌
df.info() # 데이터 정보 (데이터 타입 숫자 등등 알려줌)

df.head() # 처음 5개의 row를 가져옴
df.head(7) # 7개 가져옴

df.tail() # 마지막 5개 가져옴
df.tail(3) # 마지막 3개 가져옴

df.values # 값 
df.index # 인덱스 가져옴
df.columns # 칼럼 
df.shape # row column 숫자 가져옴

# series 확인
df['키'].describe()
df['키'].nlargest(3) # 키큰 사람 순서대로 3명
