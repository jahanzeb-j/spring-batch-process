package com.bprocess.batchscheduler.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@Slf4j
public class BaseController {

    @RequestMapping("/")
    public String returnHtml(){
        return "index.html";
    }

    @RequestMapping("/err")
    @ResponseBody
    public String logError(){
        log.error("Error....### << Call >>> ###");
        return "Error";
    }

    @RequestMapping("/res")
    @ResponseBody
    public ResponseEntity responseJson(){
        log.info("JSON....### << Call >>> ###");
        return ResponseEntity.ok("OK");
    }

}
