"use strict";

const id = document.querySelector("#id"),
    pw = document.querySelector("#pw"),
    loginbtn = document.querySelector("button");

loginbtn.addEventListener("click",login);

function login() {
    if(!id.value) return alert("아이디를 입력해주십시오.");
    if(!pw.value) return alert("비밀번호를 입력해주세요.");
    const req = {
        id : id.value,
        pw : pw.value
    };
    fetch("/login",{
        method : "POST",
        headers : {
            "Content-Type": "application/json"
        },
        body : JSON.stringify(req),
    // TODO: Promise 공부하기
    }).then(res => res.json())
    .then(res => {
        if(res.success === true) {
            location.href = "/";
        }else{
            if(res.err) return alert(res.err);
            alert(res.msg);
        }
    })
    .catch((err) => {
        console.error(new Error("로그인중 에러 발생"))
    }); 
}