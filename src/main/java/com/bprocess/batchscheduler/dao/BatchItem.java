package com.bprocess.batchscheduler.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BatchItem extends BaseDao {

    private int batchNumber;
    private String name;
    private String desc;

}
