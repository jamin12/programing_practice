#%%
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
# %%
x = [1,2,3]
y = [2,4,8]
# %%
plt.plot(x,y,label='무슨 데이터')
plt.legend()
# %%
plt.plot(x,y,label='무슨 데이터')
plt.legend(loc='upper right')
# %%
plt.plot(x,y,label='무슨 데이터')
plt.legend(loc='lower right')
# %%
plt.plot(x,y,label='무슨 데이터')
plt.legend(loc=(0.5,0.5)) # x 축 y 축 (0~1 사이)
# %%
