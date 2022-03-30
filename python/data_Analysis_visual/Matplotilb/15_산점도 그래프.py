#%%
from matplotlib import cm
import pandas as pd
import matplotlib.pyplot as plt
import matplotlib
matplotlib.rcParams['font.family'] = 'Malgun gothic'
matplotlib.rcParams['font.size'] = 15
matplotlib.rcParams['axes.unicode_minus'] = False # 한글 폰트 사용 시 ,마이너스 글자가 깨지는 현상 해결
df = pd.read_excel('../../../score.xlsx')
# %%
df['학년'] = [3,3,2,1,1,3,2,2]

# %%
plt.scatter(df['영어'],df['수학'])
plt.xlabel('영어 점수')
plt.ylabel('수학 점수')
# %%
import numpy as np
sizes = np.random.rand(8) * 1000

# %%
plt.scatter(df['영어'],df['수학'],s=sizes)
plt.xlabel('영어 점수')
plt.ylabel('수학 점수')
# %%
sizes = df['학년'] * 500
plt.scatter(df['영어'],df['수학'],s=sizes)
plt.xlabel('영어 점수')
plt.ylabel('수학 점수')
# %%
plt.figure(figsize=(7,7))
plt.scatter(df['영어'],df['수학'],s=sizes,c=df['학년'],cmap='viridis',alpha=0.3)
plt.xlabel('영어 점수')
plt.ylabel('수학 점수')
plt.colorbar(ticks=[1,2,3], label = '학년',shrink=0.3)
# %%
