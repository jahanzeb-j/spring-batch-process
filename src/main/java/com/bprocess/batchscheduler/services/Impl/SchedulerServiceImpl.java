package com.bprocess.batchscheduler.services.Impl;

import com.bprocess.batchscheduler.repository.SchedulerRepository;
import com.bprocess.batchscheduler.repository.entity.SchedulerEntity;
import com.bprocess.batchscheduler.services.SchedulerService;
import com.bprocess.batchscheduler.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    
    @Autowired
    private SchedulerRepository schedulerRepository;

    @Override
    public boolean checkSchedulerStatus() {
        Long id = Long.valueOf(Constants.schedulerbProcessId);
        boolean status = false;
        Optional<SchedulerEntity> opSchedulerEntity = schedulerRepository.findById(id);
        SchedulerEntity schedulerEntity = null;

        if(opSchedulerEntity.isPresent()) {
            schedulerEntity = opSchedulerEntity.get();
            System.out.println(schedulerEntity.getStatus());
            switch (schedulerEntity.getStatus()) {
                case 0:
                    status = false;
                    break;
                case 1:
                    status = true;
                    break;
            }
        }
       return status;
    }

    @Override
    public boolean setSchedulerStatus(boolean value) {
        int status = 0;
        SchedulerEntity schedulerEntity = new SchedulerEntity();
        if (!(value)) {
            status = 0;
        } else if (value) {
            status = 1;
        }
        schedulerEntity.setId(Long.valueOf(Constants.schedulerbProcessId)); // batchProcess scheduler
        schedulerEntity.setName(Constants.schedulerbProcessName);
        schedulerEntity.setStatus(status);

        schedulerRepository.save(schedulerEntity);
        
        return value;
    }
}
