from sqlalchemy import Column,String,SMALLINT
from sqlalchemy.ext.declarative import declarative_base

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