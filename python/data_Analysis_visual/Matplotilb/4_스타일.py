#%%
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결 
x = [1,2,3]
y = [2,4,8]
#%%
plt.plot(x,y)
# %%
plt.plot(x,y,linewidth = 5)
# %%
## 마커(marker)
plt.plot(x,y,marker='o')
# %%
plt.plot(x,y,marker='o',linestyle='None')
#%%
plt.plot(x,y,marker='v',markersize=10)
#%%
plt.plot(x,y,marker='X',markersize=10)
#%%
plt.plot(x,y,marker='X',markersize=10,markeredgecolor='red')
#%%
plt.plot(x,y,marker='o',markersize=10,markeredgecolor='red')
#%%
plt.plot(x,y,marker='o',markersize=10,markeredgecolor='red',markerfacecolor='yellow')

#%%
# 선 스타일
plt.plot(x,y,linestyle=':')
# %%
plt.plot(x,y,linestyle='--')
#%%
plt.plot(x,y,linestyle='-.')

#%%
# 색깔
plt.plot(x,y,color='pink')
#%%
plt.plot(x,y,color='#ff0000')
# %%
plt.plot(x,y,color='b')
#%%
plt.plot(x,y,color='g')

#%%
# 포맷
plt.plot(x,y,'ro--') #color, marker linestyle
# %%
plt.plot(x,y,'bv:')
# %%
plt.plot(x,y,'go-')

#%%
# 축약어
plt.plot(x,y,marker='o',mfc='red',ms=10,ls='--')

#%%
# 투명도
plt.plot(x,y,marker='o',mfc='red',ms=10,alpha=0.3)
# %%
# 그래프 크기
plt.figure(figsize=(10,5))
plt.plot(x,y)
# %%
plt.figure(figsize=(5,10))
plt.plot(x,y)
# %%
plt.figure(figsize=(10,5),dpi=50)
plt.plot(x,y)

# %%
# 배경색
plt.figure(facecolor='yellow')
plt.plot(x,y)