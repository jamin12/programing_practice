# Matpoltlob
# 다양한 형태의 그래프를 통해서 데이터 시각화를 할 수 있는 라이브러리
#%%
import matplotlib.pyplot as plt
import matplotlib

#그래프 기본
x = [1,2,3]
y = [2,4,8]

#%%
plt.plot(x,y)
# %%
# title 설정
plt.plot(x,y)
plt.title('line graph')
#%%
plt.title('꺾은선 그래프')
# %%
#한글 폰트 설정
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
plt.title('꺾은선 그래프')
# %%
import matplotlib.font_manager as fm
[f.name for f in fm.fontManager.ttflist] # 사용 가능한 폰트
# %%
plt.plot([-1,0,1],[-5,-1,2])

