package com.bprocess.batchscheduler.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BatchJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Starting .. Job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After End.. Job");
        if(jobExecution.getStatus() == BatchStatus.FAILED){
            log.error("Job End with Error ...");
        }
    }
}
