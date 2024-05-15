package com.key4.visualizr.controller;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.LogsEntity;
import com.key4.visualizr.repository.LogsRepository;
import com.key4.visualizr.service.impl.ErrorService;
import com.key4.visualizr.service.impl.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class controller {

    @Autowired
    ErrorService errorService;

    @Autowired
    LogsService logsService;

    @GetMapping("logs")
    public List<LogsEntity> getAllLogs(){
        return logsService.fetchAllLogs();
    }

    @GetMapping("errors")
    public List<ErrorEntity> getAllErrors(){
        return errorService.fetchAllErrors();
    }

}
