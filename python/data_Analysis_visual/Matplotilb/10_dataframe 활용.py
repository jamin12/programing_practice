#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
# %%
df = pd.read_excel('../../../score.xlsx')
# %%
plt.plot(df['지원번호'],df['키'])
# %%
plt.plot(df['지원번호'],df['영어'])
plt.plot(df['지원번호'],df['수학'])

# %%
plt.plot(df['지원번호'],df['영어'])
plt.plot(df['지원번호'],df['수학'])

plt.grid(axis='y',color='purple',alpha=0.5,linestyle='--')