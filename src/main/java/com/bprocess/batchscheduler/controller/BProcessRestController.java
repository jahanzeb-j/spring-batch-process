package com.bprocess.batchscheduler.controller;

import com.bprocess.batchscheduler.dto.TaskQueueDto;
import com.bprocess.batchscheduler.services.ItemProcessService;
import com.bprocess.batchscheduler.services.TaskQueueService;
import com.bprocess.batchscheduler.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/process")
@Slf4j
public class BProcessRestController {

    @Autowired
    private ItemProcessService itemProcessService;

    @Autowired
    private TaskQueueService taskQueueService;

    @GetMapping("/getAll")
    public ResponseEntity getAllProcess(){
        log.info("-----------<<  API   >>---------");
        log.info("... GetAll Process items ...");

        List<TaskQueueDto> taskQueueDto = taskQueueService.getAllProcess();

        return ResponseEntity.ok(new BaseResponse().Success(taskQueueDto));
    }


}
