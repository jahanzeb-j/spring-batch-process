package com.bprocess.batchscheduler.services;

public interface SchedulerService {

    boolean checkSchedulerStatus();

    boolean setSchedulerStatus(boolean value);
}
