"use strict";

const logger = require("../../config/logger");
const User = require('../../model/User');

const output = {
    hello: (req,res) => {
        res.render("home/index",);
    },
    login: (req,res) => {
        res.render("home/login");
    },
    register: (req,res) => {
        res.render("home/register");
    },
};

const process = {
    login: async (req,res) => {
        const user = new User(req.body);
        const response = await user.login();
        const url = {
            method: "POST",
            path: "/login",
            status: response.err ? 400 : 200
        };
        log(response,url);
        return res.status(url.status).json(response);
        // const id = req.body.id,
        //     pw = req.body.pw;
        // const users = UserStorage.getUsers("id","pw");
        // const response = {}
        // if(users.id.includes(id)){
        //     const idx = users.id.indexOf(id);
        //     if(users.pw[idx] === pw){
        //         response.success = true;
        //         return res.json(response);
        //     }
        // }
        // response.success = false;
        // response.msg = "로그인에 실패하였습니다."
        // return res.json(response);
    },
    register: async (req,res) => {
        const user = new User(req.body);
        const response = await user.register();
        const url = {
            method: "POST",
            path: "/register",
            status: response.err ? 400 : 201
        };
        log(response,url);
        return res.status(url.status).json(response);

    },
};


module.exports = {
    output,
    process
};

const log = (response,url) => {
    if(response.err){
        logger.error(`${url.method} ${url.path} ${url.status} error: "success : ${response.success}, err : ${response.err}"`);
    }
    else{
        logger.info(`${url.method} ${url.path} ${url.status} info: "success : ${response.success}, msg : ${response.msg || ""}"`);
    }
}