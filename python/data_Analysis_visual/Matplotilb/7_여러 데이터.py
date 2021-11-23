#%%
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결 
x = [1,2,3]
y = [2,4,8]
# %%
plt.plot(x,y)
# %%
# 코로롱 백신 종류별 접종 인구
days = [1,2,3] # 1일 2일 3일
az = [2,4,8] # (단위 : 만명) 1일부터 3일까지 아스트라제네카 접종 인구
pfizer = [5,1,3] # 화이자
moderna = [1,2,5] # 모더나

plt.plot(days,az)
plt.plot(days,pfizer)
plt.plot(days,moderna)

# %%
plt.plot(days,az,label='az')
plt.plot(days,pfizer,label='pfizer',marker='o',linestyle='--')
plt.plot(days,moderna,label='morderna',marker='s',ls='-.')

plt.legend()
# %%
plt.plot(days,az,label='az')
plt.plot(days,pfizer,label='pfizer',marker='o',linestyle='--')
plt.plot(days,moderna,label='morderna',marker='s',ls='-.')

plt.legend(ncol=3)