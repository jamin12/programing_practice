#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
#%%
df = pd.read_excel('stat_142801.xls',skiprows=2,nrows=2,index_col=0)
# %%
df.index.values
df.rename(index={'출생아\xa0수':'출생아 수','합계\xa0출산율':'합계 출산율'},inplace=True)
# %%
df = df.T
# %%
plt.plot(df.index,df['출생아 수'])
plt.plot(df.index,df['합계 출산율'])

# %%
fig,ax1 = plt.subplots(figsize=(10,7))
ax1.plot(df.index,df['출생아 수'],color='#ff812d')

ax2 = ax1.twinx() # x축을 공유하는 ax2
ax2.plot(df.index,df['합계 출산율'],color='#ffd100a')
# %%
fig,ax1 = plt.subplots(figsize=(13,5))
fig.suptitle('출생아 수 및 합계 출산율')
ax1.set_ylabel('출생아 수 (천 명)')
ax1.set_ylim(250,700)
ax1.set_yticks([300,400,500,600])
ax1.bar(df.index,df['출생아 수'],color='#ff812d')
for idx,val in enumerate(df['출생아 수']):
    ax1.text(idx,val+12,val,ha='center')

ax2 = ax1.twinx() # x축을 공유하는 ax2
ax2.set_ylabel('합계 출산율 (가임여성 1명당 명')
ax2.set_ylim(0,1.5)
ax2.set_yticks([0,1])
ax2.plot(df.index,df['합계 출산율'],color='#ffd100',marker='o',ms=15,lw=5,mec='w',mew=3)
for idx,val in enumerate(df['합계 출산율']):
    ax2.text(idx,val+0.08,val,ha='center')
# %%
