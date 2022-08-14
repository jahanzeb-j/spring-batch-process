package com.bprocess.batchscheduler.batch.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class BatchJobListener implements JobExecutionListener {

    private FileReader fileReader;
    private final Resource[] fileResources;

    private String fileReadPath;

    private final String folderSuccessPath;

    private final String folderErrorPath;

    private String fileName="processed";

    public BatchJobListener(Resource[] fileResources, String filePath, String folderSuccessPath, String folderErrorPath) {
        this.fileResources = fileResources;
        this.folderSuccessPath = folderSuccessPath;
        this.folderErrorPath = folderErrorPath;
    }

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
            for (Resource res: fileResources ) {
                fileReadPath = res.getFile().getAbsolutePath();
                fileName = res.getFile().getName();
                log.info("Moved file  {} to Success folder", fileName);
                this.moveFileToFolder(jobExecution.getExitStatus().getExitCode());
            }
//            this.moveFileToFolder(jobExecution.getExitStatus().getExitCode());
        } catch (IOException e) {
            log.error("Error move File afterStep: {}", e);
            //throw new CompraItemWritterException("Falha no afterStep: " + e.getMessage(), e);
        }

    }

    private void moveFileToFolder(String exitStatus) throws IOException {
        final Path source = Paths.get(fileReadPath);
        String[] extension = fileName.split("\\.");
        String formattedDate = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date().getTime());
        fileName = extension[0] + "-data-"
                + formattedDate
                +"."+extension[1] ;
        switch(exitStatus) {
            case "COMPLETED":
                log.info("Moved file  {} to Success folder", fileReadPath);

                if (this.fileHelper(folderSuccessPath)) {
                Files.move(source, Paths.get(folderSuccessPath+fileName)); }
                break;
            case "FAILED":
                log.error("Moved file {} to Error folder", fileReadPath);
                if (this.fileHelper(folderErrorPath)) {
                Files.move(source, Paths.get(folderErrorPath+fileName)); }
                break;
            default:
                log.warn("Files not moved. -found different job exit Status");
        }
    }

    private boolean fileHelper(String filePath) throws IOException {

        boolean res = Files.exists(Paths.get(filePath));
        if(res) {
            log.info("The directory already exists.");
        }
        else {
            Files.createDirectory(Paths.get(filePath));
            log.info("The directory has been created.");
        }
        return res;
    }
}
