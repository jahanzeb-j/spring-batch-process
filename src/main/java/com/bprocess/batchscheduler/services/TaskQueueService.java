package com.bprocess.batchscheduler.services;

import com.bprocess.batchscheduler.dto.TaskQueueDto;

import java.util.List;

public interface TaskQueueService {

    List<TaskQueueDto> getAllProcess();

}
