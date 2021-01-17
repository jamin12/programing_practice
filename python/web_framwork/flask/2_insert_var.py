from flask import Flask

app = Flask(__name__)

@app.route("/")
def hello():
    return "<h1>hello</h1>"

@app.route("/profile/<username>")
def get_profile(username):
    return "profile : " + username

@app.route("/first/<int:username>")
def get_first(username):
    return "<h3> hello %d </h3>" % (username + 5)

if __name__ == "__main__":
    app.run(host = "127.0.0.1", port="5500")