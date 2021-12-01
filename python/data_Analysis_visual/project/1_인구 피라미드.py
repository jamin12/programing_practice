#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결

#%%
# 남자 데이터 정의
df_m = pd.read_excel('201108_201108_연령별인구현황_월간.xlsx',skiprows=3,index_col='행정기관',usecols='B,E:Y')
df_m.iloc[0] = df_m.iloc[0].str.replace(',','').astype(int) 
# %%
# 여자 데이터 정의
df_f = pd.read_excel('201108_201108_연령별인구현황_월간.xlsx',skiprows=3,index_col='행정기관',usecols='B,AB:AV')
df_f.iloc[0] = df_f.iloc[0].str.replace(',','').astype(int)
# %%
df_f.columns = df_m.columns # 컬럼 명 통일
df_f.columns
#%%
# 데이터 시각화
plt.figure(figsize=(10,7))
plt.title('2011년도 대한민국 인구 분포도')
plt.barh(df_m.columns,-df_m.iloc[0] // 1000)
plt.barh(df_f.columns,df_f.iloc[0] // 1000) 
# %%
