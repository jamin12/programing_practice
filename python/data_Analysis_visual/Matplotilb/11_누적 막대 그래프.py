#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
df = pd.read_excel('../../../score.xlsx')
# %%
plt.bar(df['이름'],df['국어'])
plt.bar(df['이름'],df['영어'])
# %%
plt.bar(df['이름'],df['국어'])
plt.bar(df['이름'],df['영어'],bottom=df['국어'])
# %%
plt.bar(df['이름'],df['국어'],label='국어')
plt.bar(df['이름'],df['영어'],bottom=df['국어'],label='영어')
plt.bar(df['이름'],df['수학'],bottom=df['국어']+df['영어'],label='수학')

plt.xticks(rotation=60)
plt.legend()