package com.key4.visualizr.controller;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.service.impl.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ErrorController {

    @Autowired
    ErrorService errorService;

    private final LocalDateTime lt = LocalDateTime.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping("/errorlogs")
    public ResponseEntity<List<ErrorEntity>> getAllErrors(
    ){
        try{
            List<ErrorEntity> errorsList = errorService.getAllErrors();

            if(errorsList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(errorsList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagerrlogs")
    public ResponseEntity<Page<ErrorEntity>> getAllPaginated(
                @RequestParam(
                        name="page",
                        required = false) int page,
                @RequestParam(name="size",
                        required = false) int size,
                @RequestParam(
                        value = "orderBy",
                        required = false,
                        defaultValue = "date") String orderBy,
                @RequestParam(
                        name = "dir",
                        required = false,
                        defaultValue = "-1") int direction,
                @RequestParam(
                        name = "globalfilter",
                        required = false
                )String keyword,
                @RequestParam(
                        name = "fromDate",
                        required = false
                ) String fromDate,
                @RequestParam(
                        name = "toDate",
                        required = false
                ) String toDate
    )
        {
            if(fromDate != null){
                try{
                    Page<ErrorEntity> paginatedErrorsInRange = errorService.getErrorsInRange(
                            LocalDate.parse(fromDate,formatter),
                            toDate != null ? LocalDate.parse(toDate,formatter) : LocalDate.now(),
                            direction,
                            page, size, orderBy
                    );
                    return new ResponseEntity<>(paginatedErrorsInRange, HttpStatus.OK);
                } catch (Exception e){
                    e.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }

            if (keyword != null) {
                try {
                    Page<ErrorEntity> paginatedSearchData = errorService.fullTextResearch(page,size,keyword,direction,orderBy);
                    return new ResponseEntity<>(paginatedSearchData, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                try {
                    Page<ErrorEntity> paginatedDatas = errorService.getAllPaginated(page, size, direction, orderBy);
                    if (paginatedDatas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    return new ResponseEntity<>(paginatedDatas, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }


    @PostMapping("/errorsUpload")
    public ResponseEntity<String> uploadFile() {
        try {
            errorService.save();
            return new ResponseEntity<>("upload success",HttpStatus.CREATED);
        }catch (Exception e){
           return new ResponseEntity<>("upload fail",HttpStatus.CONFLICT);
        }
    }
}
