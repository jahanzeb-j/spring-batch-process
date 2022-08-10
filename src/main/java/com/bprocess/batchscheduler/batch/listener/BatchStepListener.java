package com.bprocess.batchscheduler.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
public class BatchStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Starting .. step");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("After .. step");
        return null;
    }
}
