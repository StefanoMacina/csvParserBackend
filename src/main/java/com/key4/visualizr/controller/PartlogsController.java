package com.key4.visualizr.controller;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.service.impl.PartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class PartlogsController {

    @Autowired
    PartlogsService ps;

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
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "orderBy",
                    required = false,
                defaultValue = "id") String orderBy,
            @RequestParam(name = "dir",
                    required = false,
                    defaultValue = "-1"
            ) int direction
    ) {
        try{
            Page<PartlogsEntity> paginatedDatas = ps.getAllPaginated(page, size, direction, orderBy);

            if(paginatedDatas.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(paginatedDatas, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/partslogupload")
    public ResponseEntity<String> uploadLogs( ){
        try {
            ps.save();
            return new ResponseEntity<>("upload success", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("upload failed",HttpStatus.CONFLICT);
        }
    }
}
