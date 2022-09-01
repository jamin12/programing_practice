"use strict";

const db = require('../config/db');

class UserStorage{
    static getUserInfo(id){
        // promise 안에 함수가 성공 하면 resolve를 실행 실패하면 reject실행
        return new Promise((resolve,reject) => {
            const query = "select * from users where id= ?;"
            db.query(query,[id],(err,data) => {
                if(err) reject(err);
                else resolve(data[0]);
            });
        })
    }

    static save(userInfo){
        return new Promise((resolve,reject) => {
            const query = "insert into users(id,name,pw) values (?,?,?);";
            db.query(query,[userInfo.id,userInfo.name,userInfo.pw],(err) => {
                if(err) reject(`${err}`);
                else resolve({ success: true });
            });
        })
    }
}


module.exports = UserStorage;