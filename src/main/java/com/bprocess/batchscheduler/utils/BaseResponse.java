package com.bprocess.batchscheduler.utils;

import org.springframework.http.HttpStatus;

public class BaseResponse {

    public int code;
    public String msg;
    public Object data;

    public Object Success(Object body){
        BaseResponse bs = new BaseResponse();
        bs.code=HttpStatus.OK.value();
        bs.msg = Constants.SuccessMsg;
        bs.data=body;
        return bs;
    }

    public Object Error(Object body){
        BaseResponse bs = new BaseResponse();
        bs.code= HttpStatus.BAD_REQUEST.value();
        bs.msg = Constants.ErrorMsg;
        bs.data=body;
        return bs;
    }

    public Object result(int status,Object body,String msg){
        BaseResponse bs = new BaseResponse();
        bs.code= status;
        bs.msg= msg;
        bs.data=body;
        return bs;
    }

}
