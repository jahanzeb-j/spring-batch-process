package com.bprocess.batchscheduler.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {

    private int id;
    private int status;
    private String updatedBy;
    private String updatedDate;
    private String cratedBy;
    private String createdDate;

}
