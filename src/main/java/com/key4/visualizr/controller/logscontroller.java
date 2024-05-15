package com.key4.visualizr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class logscontroller {

    @GetMapping("logs")
    public void getAllLogs(){

    }

    @GetMapping("errors")
    public void getAllErrors(){

    }

}
