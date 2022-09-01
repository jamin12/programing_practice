"use strict";

const UserStorage = require("./userStorage");

class User{
    constructor(body){
        this.body = body;
    }

    async login(){
        const client = this.body;
        try {
            document
            const {id,pw} = await UserStorage.getUserInfo(client.id);
            if(id){
                if(id === client.id && pw === client.pw){
                    return {success: true};
                }
                return { success: false, msg: "User password not match" };
            }
            return { success: false, msg: "User id not match" };
        } catch (err) {
            return { success: false, err };
        }
    }

    async register(){
        const client = this.body;
        try {
            const response = await UserStorage.save(client);
            return response;
        } catch (err) {
            return { success: false, err };
        }
    }
}

module.exports = User;