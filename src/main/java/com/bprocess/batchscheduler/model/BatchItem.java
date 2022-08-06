package com.bprocess.batchscheduler.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchItem extends BaseModel {

    private int batchNumber;
    private String name;
    private String desc;

}
