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

@Service
public class PartlogsService implements IPartlogsService {

    @Autowired
    PartogsRepository pl;

    @Override
    public void save(){
        try {
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();

            List<PartlogsEntity> dbEntities = pl.findAll();

            logsEntities.removeIf(dbEntities::contains);

            pl.saveAll(logsEntities);

            System.out.println(logsEntities.size() + " ------------ " + dbEntities.size());


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public List<PartlogsEntity> getAllLogs() {
        return pl.findAll();
    }

    @Override
    public Page<PartlogsEntity> getAllPaginated(int page, int size, int directionNumber, String... orderBy) {

        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy));

        return pl.findAll(pageRequest);
    }

    @Override
    public Page<PartlogsEntity> getAllPaginatedInSTimeRange(LocalDate fromDate, LocalDate toDate, int page,
                                                int size, int directionNumber, String... orderBy) {

        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy));

        return pl.getPartLogsBetweenSTime(fromDate, toDate, pageRequest);
    }

    @Override
    public Page<PartlogsEntity> getAllPaginatedInETimeRange(LocalDate fromDate, LocalDate toDate, int page,
                                                                int size, int directionNumber, String... orderBy) {

        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy));

        return pl.getPartLogsBetweenETime(fromDate, toDate, pageRequest);
    }

    @Override
    public Page<PartlogsEntity> fullTextResearch(int page, int size, String keyword, int direction, String... orderBy) {

        PageRequest pageRequest = PageRequest.of(page,size,Sort.Direction.fromString(
                direction != -1 ? "asc" : "desc"
        ),orderBy);

        return pl.search(String.valueOf(keyword),pageRequest);
    }

}
