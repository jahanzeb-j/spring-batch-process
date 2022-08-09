package com.bprocess.batchscheduler.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchItem extends BaseDto {

    private int batchNumber;
    private String name;
    private String desc;

}
