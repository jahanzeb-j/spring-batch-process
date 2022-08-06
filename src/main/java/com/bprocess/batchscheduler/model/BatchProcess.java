package com.bprocess.batchscheduler.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchProcess extends BaseModel {

    private int batchItemNum;
    private int batchItemId;

    private int progress;
    private String progressStatus;

    private int itemProcessed;
    private int itemPending;
    private int itemTotal;
    private int itemInvalid;

    private String jobStartTime;
    private String jobEndTime;
    private String jobRequestTime;


}
