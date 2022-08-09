package com.bprocess.batchscheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDto {

    private int id;
    private int status;
    private String updatedBy;
    private String updatedDate;
    private String cratedBy;
    private String createdDate;

}
