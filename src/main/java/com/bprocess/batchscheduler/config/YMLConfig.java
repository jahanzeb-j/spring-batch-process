package com.bprocess.batchscheduler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "file.reader")
@Data
public class YMLConfig {
    private String path;
}
