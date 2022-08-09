package com.bprocess.batchscheduler.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDao {

    private int id;
    private int status;
    private String updatedBy;
    private String updatedDate;
    private String cratedBy;
    private String createdDate;

}
