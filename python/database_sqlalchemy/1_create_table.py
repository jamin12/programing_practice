from sqlalchemy import create_engine,Column,SMALLINT,BIGINT,String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

#데이터 베이스와 연결
engine = create_engine("mysql+pymysql://root:1234@localhost:3306/database_practice",echo=False)

#ORM을 사용할 때 처리할 데이터베이스를 설명하고 해당 테이블에 매핑될 클래스를 정의하는 작업이 필요
# 매핑 선언
Base = declarative_base()

class user_test(Base):
    __tablename__  = "user_test"
    name = Column(String(20),primary_key=True)
    age = Column(SMALLINT)
    local = Column(String(20))

    def __init__(self,name,age,local):
        self.name = name
        self.age = age
        self.local = local

    def __repr__(self):
        return "<user_test('%s','%d','%s')>" %(self.name,self.age,self.local)

#테이블 생성
def create_table(engine):
    Base.metadata.create_all(engine)
#세션을 통해 데이터베이스를 다룰 수 있다
Session = sessionmaker(bind=engine)

if __name__ == "__main__":
    a = user_test('a',21,'aa')
    session = Session()
    #세션은 커밋을 통해 데이터베이스에 명령을 전달한다 실패시 session.rolback을 통해 이유를 본다
    # 
    # session.add(a)
    # session.add_all([
    #     user_test('b',22,'bb'),
    #     user_test('c',23,'cc'),
    #     user_test('d',24,'dd')
    # ])

    #첫번 째 결과만 가져올수 있음
    our_user = session.query(user_test).all()
    #이런식으로도 값 변경 가능
    # our_user.local = 'aaa'
    #변경한 값 확인
    # print(session.dirty)
    # 아직 커밋 안된 문장
    # print(session.new)
    for i in our_user:
        # print(i.name,i.age)
        print(i.user_test,i.name)
    session.commit()


