package com.key4.visualizr.controller;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.service.impl.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ErrorController {

    @Autowired
    ErrorService errorService;

    @GetMapping("/errors")
    public ResponseEntity<List<ErrorEntity>> getAllErrors(){
        try{
            List<ErrorEntity> errorsList = errorService.fetchAllErrors();

            if(errorsList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(errorsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/")
    public void uploadFile() {
        try {
            errorService.save();
        }catch (Exception ignored){

        }
    }


}
