import requests
import json

url = "http://localhost:8000/items/"

data = {"name": ["new_challenge","hiohi"], "price":2020, "tax":2021}

res = requests.post(url,json=data)

print(res.text)