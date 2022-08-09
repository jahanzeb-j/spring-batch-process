package com.bprocess.batchscheduler.services;

import org.springframework.stereotype.Service;

@Service
public interface ItemProcessService {

    boolean isRunning();
    String checkVal();
}
