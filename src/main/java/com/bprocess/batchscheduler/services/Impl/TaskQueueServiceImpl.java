package com.bprocess.batchscheduler.services.Impl;

import com.bprocess.batchscheduler.dto.TaskQueueDto;
import com.bprocess.batchscheduler.repository.TaskQueueRepository;
import com.bprocess.batchscheduler.repository.entity.TaskQueueEntity;
import com.bprocess.batchscheduler.services.TaskQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskQueueServiceImpl implements TaskQueueService {
    
    @Autowired
    private TaskQueueRepository taskQueueRepository;

    @Override
    public List<TaskQueueDto> getAllProcess() {

      List<TaskQueueEntity> taskQueueEntityList =  taskQueueRepository.findAll();
      List<TaskQueueDto> taskQueueDtoList = new ArrayList<>();

      taskQueueEntityList.stream().forEach(taskQueueEntity -> {
          TaskQueueDto taskQueueDto = new TaskQueueDto();
          taskQueueDto.setContent(taskQueueEntity.getContent());
          taskQueueDto.setId(taskQueueEntity.getId());
          taskQueueDtoList.add(taskQueueDto);
      });
      return taskQueueDtoList;
    }
}
