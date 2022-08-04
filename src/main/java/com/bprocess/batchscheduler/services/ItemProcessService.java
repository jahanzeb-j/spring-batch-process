package com.bprocess.batchscheduler.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemProcessService {

    public String checkVal(){

        log.info("Item Processing Started ..");

        return  "Done";
    }

}
