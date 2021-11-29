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
plt.plot(x,y)
plt.title('꺾은선 그래프', fontdict={'family':'HYGungSO-Bold','size':20}) # 개별 폰트 설정
# %%
plt.plot(x,y)
plt.xlabel('x축', color='red')
plt.ylabel('y축', color='#00aa00')
# %%
plt.plot(x,y)
plt.xlabel('x축', color='red', loc='right') #left center right
plt.ylabel('y축', color='#00aa00', loc='top') # top center bottom
# %%
plt.plot(x,y)
plt.xticks([1,2,3])
plt.yticks([3,6,9,12])
# %%
