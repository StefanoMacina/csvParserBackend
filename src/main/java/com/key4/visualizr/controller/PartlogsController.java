package com.key4.visualizr.controller;
import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.service.impl.PartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PartlogsController {

    @Autowired
    PartlogsService ps;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @GetMapping("/partlogs")
    public ResponseEntity<List<PartlogsEntity>> getAllLogs(){
        try {
            List<PartlogsEntity> logsList = ps.getAllLogs();

            if(logsList.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(logsList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagpartlogs")
    public ResponseEntity<Page<PartlogsEntity>> getAllPaginated(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size,
            @RequestParam(name = "orderBy", required = false, defaultValue = "startTime") String orderBy,
            @RequestParam(name = "dir", required = false, defaultValue = "-1") int direction,
            @RequestParam(name = "globalfilter", required = false) String keyword,
            @RequestParam(name = "range", required = false) String range,
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate
    ) {
        if (range != null) {

            if(fromDate == null && toDate == null){
                try {
                    Page<PartlogsEntity> paginatedDatas = ps.getAllPaginated(page, size, direction,"startTime" );

                    if (paginatedDatas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

                    return new ResponseEntity<>(paginatedDatas, HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
                switch (range) {
                    case "startTime": {
                        try {
                            Page<PartlogsEntity> startTimeRange = ps.getAllPaginatedInSTimeRange(
                                    fromDate != null ? LocalDate.parse(fromDate, formatter) :  LocalDate.parse("1980-10-01",formatter),
                                    toDate != null ? LocalDate.parse(toDate, formatter) : LocalDate.now(),
                                    page, size, direction, "start_time"
                            );
                            return new ResponseEntity<>(startTimeRange, HttpStatus.OK);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                    case "endTime": {
                        try {
                            Page<PartlogsEntity> endTimeRange = ps.getAllPaginatedInETimeRange(
                                    fromDate != null ? LocalDate.parse(fromDate, formatter) : LocalDate.parse("1980-10-01",formatter),
                                    toDate != null ? LocalDate.parse(toDate, formatter) : LocalDate.now(),
                                    page, size, direction, "start_time"
                            );
                            return new ResponseEntity<>(endTimeRange, HttpStatus.OK);
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                }

        }

        if (keyword != null) {
            try {
                Page<PartlogsEntity> paginatedSearchData = ps.fullTextResearch(page, size, keyword, direction, orderBy);
                return new ResponseEntity<>(paginatedSearchData, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                Page<PartlogsEntity> paginatedDatas = ps.getAllPaginated(page, size, direction, orderBy);

                if (paginatedDatas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

                return new ResponseEntity<>(paginatedDatas, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @PostMapping("/partslogupload")
    public ResponseEntity<String> uploadLogs( ){
        try {
           int recordsToAdd = ps.save();
           if(recordsToAdd > 0){
               return new ResponseEntity<>("upload success", HttpStatus.CREATED);
           }
           return new ResponseEntity<>("no records to add",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("upload failed",HttpStatus.CONFLICT);
        }
    }
}
