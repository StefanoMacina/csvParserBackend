package com.key4.visualizr.controller;

import com.key4.visualizr.model.entity.LogsEntity;
import com.key4.visualizr.service.impl.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class LogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/logs")
    public ResponseEntity<List<LogsEntity>> getAllLogs(){
        try {
            List<LogsEntity> logsList = logsService.fetchAllLogs();

            if(logsList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logsList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/")
    public void uploadLogs( ){
        try {

        }catch (Exception e){
            throw new RuntimeException("fail to upload csv data");
        }
    }
}
