from flask import Flask, render_template, request
import google_keyword


app = Flask(__name__)


@app.route('/')
def index():
    # get을 통해 전달받은 데이터 확인
    key1 = request.args.get('keyword1')
    key2 = request.args.get('keyword2')

    if not key1 or not key2:
        return render_template('9_index.html')
    else:
        # 모듈로 키워드 개수 가져오기
        value1 = google_keyword.get_keyword_number(key1)
        value2 = google_keyword.get_keyword_number(key2)

        # 사용자 보낼 데이터
        data = {key1: value1, key2: value2}

        return render_template('9_index.html', data=data)


if __name__ == "__main__":
    app.run(debug=True)
