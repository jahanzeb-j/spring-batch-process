package com.bprocess.batchscheduler.utils;

import org.springframework.http.HttpStatus;

public class BaseResponse {

    public int status;
    public String msg;
    public Object body;

    public Object Success(Object body){
        BaseResponse bs = new BaseResponse();
        bs.status=HttpStatus.OK.value();
        bs.msg = Constants.SuccessMsg;
        bs.body=body;
        return bs;
    }

    public Object Error(Object body){
        BaseResponse bs = new BaseResponse();
        bs.status= HttpStatus.BAD_REQUEST.value();
        bs.msg = Constants.ErrorMsg;
        bs.body=body;
        return bs;
    }

    public Object result(int status,Object body,String msg){
        BaseResponse bs = new BaseResponse();
        bs.status= status;
        bs.msg= msg;
        bs.body=body;
        return bs;
    }

}
