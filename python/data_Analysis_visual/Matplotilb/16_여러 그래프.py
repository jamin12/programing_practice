#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
df = pd.read_excel('../../../score.xlsx')
# %%
fig,axs = plt.subplots(2,2,figsize = (15,10))  # 2x2에 해당하는 plot들을 생성
fig.suptitle('여러 그래프 넣기')

#첫번 쨰 그래프
axs[0,0].bar(df['이름'],df['국어'],label='국어점수')
axs[0,0].set_title('첫 번째 그래픜')
axs[0,0].legend()
axs[0,0].set(xlabel='이름',ylabel='점수')
axs[0,0].set_facecolor('lightyellow')
axs[0,0].grid(linestyle = '--', linewidth=0.5)\

# 두 번째 그래프
axs[0,1].plot(df['이름'],df['수학'],label='수학 점수')
axs[0,1].plot(df['이름'],df['영어'],label='영어 점수')
axs[0,1].legend()

#세 번쨰 그래프
axs[1,0].barh(df['이름'],df['키'])

#네 번째 그래프
axs[1,1].plot(df['이름'],df['사회'],color='green',alpha=0.5)
