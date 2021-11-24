#%%
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
# %%
values = [30,25,20,13,10,2]
label = ['python','java','javascript','c#','c/c++','ETC']
# color = ['b','g','r','c','m','y']
color = ['#ffadad','#ffd6a5','#fdffb6','#caffbf','#9bf6ff','#a0c4ff']
explode = [0.05] * 6

plt.pie(values,labels=label,autopct='%.1f%%',startangle=90,counterclock=False,colors=color,explode=explode)
plt.show()
# %%
wedeprops={'width':0.6}
plt.pie(values,labels=label,autopct='%.1f%%',startangle=90,counterclock=False,colors=color,explode=explode,wedgeprops=wedeprops)
plt.show()
# %%
wedeprops={'width':0.6, 'edgecolor':'w','linewidth':2}
plt.pie(values,labels=label,autopct='%.1f%%',startangle=90,counterclock=False,colors=color,wedgeprops=wedeprops)
plt.show()
#%%
def custom_autopct(pct):
    return ('%.0f%%' % pct) if pct >= 10 else ''

plt.pie(values,labels=label,autopct=custom_autopct,startangle=90,counterclock=False,colors=color,wedgeprops=wedeprops,pctdistance=0.7)
plt.show()
# %%
df = pd.read_excel('../../../score.xlsx')

# %%
grp = df.groupby('학교')
values = [grp.size()['북산고'],grp.size()['능남고']]
label = ['북산고','능남고']

plt.pie(values,labels=label)
plt.title('소속학교')
plt.show()