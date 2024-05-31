package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.repository.PartogsRepository;
import com.key4.visualizr.service.IPartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartlogsService implements IPartlogsService, CommandLineRunner {

    @Autowired
    PartogsRepository pl;

    @Override
    public int save(){
        try {
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();

            List<PartlogsEntity> dbEntities = pl.findAll();

            logsEntities.removeIf(dbEntities::contains);

            Map<String, PartlogsEntity> groupedEntities = logsEntities.stream()
                    .collect(Collectors.toMap(
                            e -> e.getLog_index() + "-" + e.getStart_time(),
                            e -> e,
                            (e1, e2) -> {
                                if (e1.getEnd_time() == null && e2.getEnd_time() != null) {
                                    e1.setEnd_time(e2.getEnd_time());
                                    e1.setCompleted(e2.getCompleted());
                                }
                                return e1;
                            },
                            LinkedHashMap::new
                    ));

            List<PartlogsEntity> groupedlogs = new ArrayList<>(groupedEntities.values());
            List<PartlogsEntity> uniqueEntities = new ArrayList<>(new HashSet<>(groupedlogs));

            pl.saveAll(uniqueEntities);

            return uniqueEntities.size();


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
                ),orderby);

        if (keyword == null || keyword.isBlank()) {
            if ("start_time".equals(range)) {
                return pl.findByStartTimeBetween(fromDate, toDate, pr);
            } else {
                return pl.findByEndTimeBetween(fromDate, toDate, pr);
            }
        }
        if ("start_time".equals(range)) {
            return pl.searchInRangeByStartTime(fromDate, toDate, keyword, pr);
        } else {
            return pl.searchInRangeByEndTime(fromDate, toDate, keyword, pr);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        save();
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
