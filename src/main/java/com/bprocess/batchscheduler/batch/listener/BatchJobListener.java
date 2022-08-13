package com.bprocess.batchscheduler.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class BatchJobListener implements JobExecutionListener {

    private FileReader fileReader;
    private String fileReadPath;
    private String folderSucessPath;
    private String folderErrorPath;
    private String fileName;


    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Starting .. Job");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job End..");
        if(jobExecution.getStatus() == BatchStatus.FAILED){
            log.error("Job End with Error ...");
        }

        // Move files to respective folder after process
        try {
            //fileReader.closeLineNumberReader();
            this.moveFileToFolder(jobExecution.getExitStatus().getExitCode());
        } catch (IOException e) {
            log.error("Error afterStep: {}", e.getMessage());
            //throw new CompraItemWritterException("Falha no afterStep: " + e.getMessage(), e);
        }

    }

    private void moveFileToFolder(String exitStatus) throws IOException {
        switch(exitStatus) {
            case "COMPLETED":
                log.info("Moved file  {} to Success folder", fileName);
               // Files.move(Paths.get(fileReadPath), Paths.get(folderSucessPath+fileName));
                break;
            case "FAILED":
                log.error("Moved file  {} to Error folder", fileName);
               // Files.move(Paths.get(fileReadPath), Paths.get(folderErrorPath+fileName));
                break;
            default:
                log.warn("Files not moved. -found different job exit Status");
        }
    }
}
