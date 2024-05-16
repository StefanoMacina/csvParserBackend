package com.key4.visualizr.controller;

import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.service.impl.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PartlogsController {

    @Autowired
    LogsService logsService;

    @GetMapping("/partlogs")
    public ResponseEntity<List<PartlogsEntity>> getAllLogs(){
        try {
            List<PartlogsEntity> logsList = logsService.getAllLogs();

            if(logsList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logsList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagpartlogs")
    public Page<PartlogsEntity> getAllPaginated(
        @RequestParam int page,
        @RequestParam int size
    ) {
        return logsService.getAllPaginated(page,size);
    }


    @PostMapping("/partslogupload")
    public void uploadLogs( ){
        try {
            logsService.save();
        }catch (Exception e){
            throw new RuntimeException("fail to upload csv data");
        }
    }
}
