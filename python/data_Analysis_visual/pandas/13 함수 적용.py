#함수 적용

from numpy import add
import pandas as pd
df = pd.read_excel('score.xlsx',index_col='지원번호')

##데이터에 함수 적용 (apply)

#키 뒤에 cm을 붙이는 역할
def add_cm(height):
    return str(height) + 'cm'

df['키'] = df['키'].apply(add_cm)

def capitalize(lang):
    if pd.notnull(lang): #nan이 아닌지
        return lang.capitalize() # 첫 글자는 대문자로 , 나머지느 소문자로
    return lang

df['SW특기'] = df['SW특기'].apply(capitalize)

print(df)