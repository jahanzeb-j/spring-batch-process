package com.bprocess.batchscheduler.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseResponse {

    public int status;
    public String msg;
    public Object body;

    public Object Success(Object body){
        BaseResponse bs = new BaseResponse();
        bs.status=HttpStatus.OK.value();
        bs.msg="SUCCESS";
        bs.body=body;
        return bs;
    }

    public Object Error(Object body){
        BaseResponse bs = new BaseResponse();
        bs.status= HttpStatus.BAD_REQUEST.value();
        bs.msg="ERROR";
        bs.body=body;
        return bs;
    }


}
