package com.bprocess.batchscheduler.services.Impl;

import com.bprocess.batchscheduler.services.ItemProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemProcessServiceImpl implements ItemProcessService {

    /*
       Return the running process
     */

    public boolean isRunning() {
        boolean isProcess = true;
        //
        return isProcess;
    }

    public String checkVal(){
        log.info("Item Processing Started ..");
        return  "Done";
    }

}
