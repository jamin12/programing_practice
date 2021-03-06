import requests
import re
from bs4 import BeautifulSoup

headers = {"User-Agent" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Whale/2.8.105.22 Safari/537.36"}

for i in range(1,6):
    # print("페이지 : " i)
    url = "https://www.coupang.com/np/search?q=%EB%85%B8%ED%8A%B8%EB%B6%81&channel=recent&component=&eventCategory=SRP&trcid=&traid=&sorter=scoreDesc&minPrice=&maxPrice=&priceRange=&filterType=&listSize=36&filter=&isPriceRange=false&brand=&offerCondition=&rating=0&page={}&rocketAll=false&searchIndexingToken=1=4&backgroundColor=".format(i)
    res = requests.get(url,headers = headers)
    res.raise_for_status()
    soup = BeautifulSoup(res.text,"lxml")


    items = soup.find_all("li", attrs = {"class" : re.compile("^search-product")})

    for item in items:
        # 광고 제품은 제외
        ad_badge = item.find("span", attrs = {"class" : "ad-badge-text"})
        if ad_badge:
            # print(" <광고 상품 제외합니다.> ")
            continue

        name = item.find("div",attrs = {"class" : "name"}).get_text() # 제품 이름 가져오기       
        # 애플 제움 제외
        if "Apple" in name:
            # print(" <Apple 상품 제외합니다.>")
            continue

        price = item.find("strong", attrs = {"class":"price-value"}).get_text() # 제품 가격 가져오기
    
        # 제품 링크 가져오기
        link = "https://www.coupang.com" + item.find("a", attrs = {"class" : "search-product-link"})["href"]
    # 리뷰 50개 이상, 평점 4.5 이상 되는 것만 조회
        rate = item.find("em", attrs = {"class":"rating"}) # 제품 평점 가져오기
        if rate:
            rate = rate.get_text()
        else:
            # print(" <평점 없는 상품 제외합니다.>")
            continue
        count = item.find("span", attrs = {"class":"rating-total-count"}) # 제품 평점 수 가져오기
        if count:
            count = count.get_text()[1:-1]
        else:
            # print(" <평점 수 없는 상품 제외합니다.>")
            continue
            
        if float(rate) >= 4.5 and int(count) >= 50:
            # print(name,price,rate,count)
            print(f"제품명 : {name}")
            print(f"가격 : {price}")
            print(f"평점 : {rate}점 ({count})개")
            print(f"바로가기 : {link}")
            print("-"*100)



