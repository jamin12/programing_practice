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
plt.savefig('graph.png',dpi=100)
# %%
plt.figure(dpi=200)
plt.plot(x,y)
plt.savefig('graph200.png',dip=100)
# %%
