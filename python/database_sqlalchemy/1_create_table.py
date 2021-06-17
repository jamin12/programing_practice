from sqlalchemy import create_engine, Column, SMALLINT, BIGINT, String, and_, or_
from sqlalchemy import (
    Column,
    Integer,
    String,
    DateTime,
    func,
    Enum,
    Boolean,
    ForeignKey,
)
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, relationship

#데이터 베이스와 연결
engine = create_engine(
    "mysql+pymysql://root:1234@localhost:3306/sqlalchemy?charset=utf8mb4",
    echo=False)

#ORM을 사용할 때 처리할 데이터베이스를 설명하고 해당 테이블에 매핑될 클래스를 정의하는 작업이 필요
# 매핑 선언
Base = declarative_base()


class user_test(Base):
    __tablename__ = "user_test"
    name = Column(String(20), primary_key=True)
    age = Column(SMALLINT)
    local = Column(String(20))

    def __init__(self, name, age, local):
        self.name = name
        self.age = age
        self.local = local


class test_gi(Base):
    __tablename__ = "test_gi"
    name = Column(String(20), primary_key=True)
    age = Column(SMALLINT)


class parents(Base):
    __tablename__ = "parents"
    p_id = Column(Integer, primary_key=True, index=True)
    p_key = Column(String(40), nullable=True)
    # user = relationship("children",back_populates="key")
    user = relationship("children", backref="parents")

    def __init__(self, p_key):
        self.p_key = p_key


class children(Base):
    __tablename__ = "children"
    c_id = Column(Integer, primary_key=True, index=True)
    c_key = Column(String(40), nullable=True)
    c_test = Column(String(20), nullable=True)
    c_pid = Column(Integer, ForeignKey("parents.p_id"), nullable=False)

    # key = relationship("parents",back_populates="user")

    def __init__(self, c_key, c_test, c_pid):
        self.c_key = c_key
        self.c_test = c_test
        self.c_pid = c_pid


#테이블 생성
def create_table(engine):
    Base.metadata.create_all(engine)


#세션을 통해 데이터베이스를 다룰 수 있다
Session = sessionmaker(bind=engine)

if __name__ == "__main__":
    # a = children('test3','test1-3',3)
    session = Session()
    create_table(engine)
    #세션은 커밋을 통해 데이터베이스에 명령을 전달한다 실패시 session.rolback을 통해 이유를 본다
    #
    # session.add(a)
    # session.add_all([
    #     user_test('b',22,'bb'),
    #     user_test('c',23,'cc'),
    #     user_test('d',24,'dd')
    # ])

    #첫번 째 결과만 가져올수 있음 first()
    # 리스트처럼 가져오기 가능
    # order_by sql 명령어 사용 가능
    # our_user = session.query(user_test).all()[1:3]
    #이런식으로도 값 변경 가능
    # our_user.local = 'aaa'
    #변경한 값 확인
    # print(session.dirty)
    # 아직 커밋 안된 문장
    # print(session.new)
    # our_user = session.query(user_test).filter(user_test.name == 'a')
    # our_user = session.query(user_test).filter(user_test.name.in_(['a','d','c']))
    # our_user = session.query(user_test).filter(~user_test.name.in_(['a','d','c']))
    # our_user = session.query(user_test).filter(and_(user_test.name == 'a', user_test.age == '21'))
    # our_user = session.query(user_test).filter(or_(user_test.name == 'a', user_test.name == 'b'))
    our_user = session.query(parents).filter(parents.p_id > 0)

    for i in our_user:
        # print(i.name,i.age)
        print(i.user[0].c_test)
    session.commit()
