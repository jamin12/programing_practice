#%%
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
# %%
labels = ['강백호','서태웅','정대만'] # 이름
values = [190,187,184] # 키

plt.barh(labels,values)
plt.xlim(175,195)
# %%
bar = plt.bar(labels,values)
bar[0].set_hatch('/') # ///
bar[1].set_hatch("x")
bar[2].set_hatch("..")

# %%
bar = plt.bar(labels,values)

for idx, rect in enumerate(bar):
    plt.text(idx,rect.get_height()+0.5,values[idx],ha='center',color='b')
    plt.ylim(175,195)
