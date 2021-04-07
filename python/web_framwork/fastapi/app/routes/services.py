import json

import os as op
from sys import path as sp
sp.append(op.path.dirname(op.path.dirname(op.path.dirname(__file__))))

from typing import List
from uuid import uuid4

import requests
from fastapi import APIRouter, Depends
from fastapi.logger import logger
from sqlalchemy.orm import Session
from starlette.requests import Request

from app.common.consts import MAX_API_KEY, MAX_API_WHITELIST
from app.database.conn import db
from app.database.schema import Users, ApiKeys, ApiWhiteLists
from app import models as m
from app.errors import exceptions as ex
import string
import secrets

from app.models import MessageOk, KakaoMsgBody

router = APIRouter(prefix='/services')


@router.get('')
async def get_all_services(request: Request):
    return dict(your_email=request.state.user.email)




@router.post('kakao/send')
async def send_kakao(request: Request, body: KakaoMsgBody):
    #https://developers.kakao.com/docs/latest/ko/message/rest-api
    #카카오톡 디벨롭에 들어가서 엑세스 키 받기
    token = op.environ.get("KAKAO_KEY", "ff7f8773-a41e-4198-91a6-3ce23715-7a41-43bd-83e1-2894cbb7c4e8")
    headers = {"Authorization": f"Bearer {token}", "Content-Type": "application/x-www-form-urlencoded"}

    body = dict(object_type="text", text=body.msg, link=dict(web_url="", mobile_url=""), button_title="")
    data = {"template_object": json.dumps(body, ensure_ascii=False)}

    res = requests.post("https://kapi.kakao.com/v2/api/talk/memo/default/send", headers=headers, data=data)
    try:
        res.raise_for_status()
        if res.json()["result_code"] != 0:
            raise Exception("KAKAO SEND FAILED")
    except Exception as e:
        print(res.json())
        logger.warning(e)
        raise ex.KakaoSendFailureEx

    return MessageOk()

