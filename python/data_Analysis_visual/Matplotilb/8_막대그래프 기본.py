#%%
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키

plt.bar(labels,values)
# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키

plt.bar(labels,values,color='r')
# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키
colors = ['r','g','b']

plt.bar(labels,values,color=colors,alpha=0.5)

# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키

plt.bar(labels,values)
plt.ylim(175,195)
# %%
plt.bar(labels,values,width=0.5)

# %%
plt.bar(labels,values,width=0.3)
plt.xticks(rotation=45)
plt.yticks(rotation=45)
# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키
ticks = ['1번학생','2번학생','3번학생']

plt.bar(labels,values)
plt.xticks(labels,ticks)
