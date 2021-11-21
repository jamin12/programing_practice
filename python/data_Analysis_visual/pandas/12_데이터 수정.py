# 12 데이터 수정

import pandas as pd
from pandas.core.indexes.base import InvalidIndexError
df = pd.read_excel('score.xlsx', index_col='지원번호')

#칼럼 수정
df['학교'].replace({'북산고': '상북고', '능남고': '무슨고'})
df['학교'].replace({'북산고': '상북고'}, inplace=True)

df['SW특기'] = df['SW특기'].str.lower()

df['학교'] = df['학교'] + '등학교'
print(df)

#칼럼 추가
df['총합'] = df['국어'] + df['영어'] + df['수학'] + df['과학'] + df['사회']
df['결과'] = 'Fail'
df.loc[df['총합'] > 400,'결과'] = 'pass' #총합이 400보다 큰 데이터 대해서 결과를 Pass로 업데이트
print(df)

#컬럼 삭제
df.drop(columns=['총합'])
df.drop(columns=['국어','영어','수학']) # inplace 해야지 반영됨

# row삭제
df.drop(index='4번')
filt = df['수학'] < 80
df.drop(df[filt].index)

#row 추가
df.loc['9번'] = ['이정환','해남고등학교',184,90,90,90,90,90,'kotlin',450,'pass']
print(df)

#cell 수정
df.loc['4번','SW특기'] = 'python' # 4번 학생의 sw특기 데이터를 python 변경
df.loc['5번',['학교','SW특기']] = ['능남고등학교','C'] # 5번학새으이 학교와 sw특기를 바꿈

#컬럼 순서 변경
cols = list(df.columns)
df = df[[cols[-1]] + cols[0:-1]] # 맨 뒤에 있는 결과 컬럼을 앞으로 가져오고 나머지 컬럼들과 합쳐 순서 변경

#컬럼 이름 변경
df.columns = ['result','name']