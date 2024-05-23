package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.repository.PartogsRepository;
import com.key4.visualizr.service.IPartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class PartlogsService implements IPartlogsService {

    @Autowired
    PartogsRepository pl;

    @Override
    public int save(){
        try {
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();

            List<PartlogsEntity> dbEntities = pl.findAll();

            logsEntities.removeIf(dbEntities::contains);

            pl.saveAll(logsEntities);

            System.out.println(logsEntities.size() + " ------------ " + dbEntities.size());

            return logsEntities.size();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }


    @Override
    public Page<PartlogsEntity> getAll(int page, int size, LocalDate fromDate,
                                           LocalDate toDate, String range,
                                           String keyword, int direction, String... orderby) {

                PageRequest pr = PageRequest.of(page,size,Sort.Direction.fromString(
                        direction != -1 ? "asc" : "desc"
                ), orderby);

        if (keyword == null || keyword.isBlank()) {
            return pl.getInRange(fromDate, toDate, pr);
        }

        if ("startTime".equals(range)) {
            return pl.getInRangeWithSearchInSTime(fromDate, toDate, keyword, pr);
        } else {
            return pl.getInRangeWithSearchInETime(fromDate, toDate, keyword, pr);
        }
    }




//    @Override
//    public List<PartlogsEntity> getAllLogs() {
//        return pl.findAll();
//    }
//
//    @Override
//    public Page<PartlogsEntity> getAllPaginated(int page, int size, int directionNumber, String... orderBy) {
//
//        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
//                directionNumber != -1 ? "asc" : "desc"
//        ),orderBy));
//
//        return pl.findAll(pageRequest);
//    }
//
//    @Override
//    public Page<PartlogsEntity> getAllPaginatedInSTimeRange(LocalDate fromDate, LocalDate toDate,  String keyword, int page,
//                                                int size, int directionNumber, String... orderBy) {
//
//        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
//                directionNumber != -1 ? "asc" : "desc"
//        ),orderBy));
//
//        return pl.getPartLogsBetweenSTime(fromDate, toDate, keyword,pageRequest);
//    }
//
//    @Override
//    public Page<PartlogsEntity> getAllPaginatedInETimeRange(LocalDate fromDate, LocalDate toDate, String keyword, int page,
//                                                                int size, int directionNumber, String... orderBy) {
//
//        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
//                directionNumber != -1 ? "asc" : "desc"
//        ),orderBy));
//
//        return pl.getPartLogsBetweenETime(fromDate, toDate, keyword,pageRequest);
//    }
//
//    @Override
//    public Page<PartlogsEntity> fullTextResearch(int page, int size, String keyword, int direction, String... orderBy) {
//
//        PageRequest pageRequest = PageRequest.of(page,size,Sort.Direction.fromString(
//                direction != -1 ? "asc" : "desc"
//        ),orderBy);
//
//        return pl.search(String.valueOf(keyword),pageRequest);
//    }
//
//
//
//    @Override
//    public Page<PartlogsEntity> fulltextInRange(int page, int size, String keyword, int direction,
//                                                LocalDate fromDate, LocalDate toDate, String... orderBy) {
//        PageRequest pr = PageRequest.of(page,size,Sort.Direction.fromString(
//                direction != -1 ? "asc" : "desc"
//        ), orderBy);
//
//        return pl.searchInRange(keyword,pr,fromDate,toDate);
//    }

}
