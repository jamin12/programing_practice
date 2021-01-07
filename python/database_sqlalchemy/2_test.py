from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from vo.user_test_vo import user_test

engine = create_engine("mysql+pymysql://root:1234@localhost:3306/database_practice",echo=False)


Sessoin = sessionmaker(bind=engine)
if __name__ == "__main__":
    session = Sessoin()
    for i in session.query(user_test).all():
        print(i.name)

