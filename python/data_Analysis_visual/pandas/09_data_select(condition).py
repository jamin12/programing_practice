# 9 데이터 선택(조건)
# 조건에 해당하는 데이터 선택 

import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

# print(df['키'] >= 185)

filt = (df['키'] >= 185)
# print(df[filt])
# print(df[~filt])

df.loc[df['키'] >= 185,'수학'] # 키가 185 이상인 학생들의 수학 데이터
df.loc[df['키'] >= 185,['이름','수학','과학']] # 키가 185 이상인 학생들의 이름 수학 과학 데이터

#다양한 조건
#& (그리고)
df.loc[(df['키'] >= 185)&(df['학교']=='북산고')] # 키가 185 이상인 학생들의 수학 데이터
# | 또는
df.loc[(df['키'] <170)|(df['키']>200)] # 키가 185 이상인 학생들의 수학 데이터

# # str 함수
filt = df['이름'].str.startswith('송') # 송씨 성을 가진 사람
# print(df[filt])

filt = df['이름'].str.contains('태') # 이름에 태가 들어간 사람
# print(df[filt])
# print(df[~filt])

langs = ['Python','Java']
filt = df['SW특기'].isin(langs) # sw특기가 python 이거나 java인사람

langs = ['python','java']
filt = df['SW특기'].str.lower().isin(langs) # sw특기가 python 이거나 java인사람 (소문자로 바꾼후 검사)

filt = df['SW특기'].str.contains('Java',na=True) # NAN 데이터에 대해서 TRUE로 설정
filt = df['SW특기'].str.contains('Java',na=False) # NAN 데이터에 대해서 False로 설정


