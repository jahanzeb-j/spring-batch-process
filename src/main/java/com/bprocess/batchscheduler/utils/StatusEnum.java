package com.bprocess.batchscheduler.utils;

public enum StatusEnum {
    ACTIVE(1),
    INACTIVE(2);

    private int status;

    private StatusEnum(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }

}
