# pandas
# 파이썬에서 사용하는 데이터 분석 라이브러리
import pandas as pd
# 1. Series
# 1차원 데이터 (정수,실수,문자열 등)

# Series 객체 생성
# 예) 1월부터 4월까지 평균 온도 데이터 (-20,-10,10,20)

temp = pd.Series([-20,-10,10,20])
# print(temp[0])

# Series 객체 생성(index 지정)
temp = pd.Series([-20,-10,10,20],index=['jan','feb','mar','apr'])
# print(temp['jan'])
