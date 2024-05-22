package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IPartlogsService {

    List<PartlogsEntity> getAllLogs();

    Page<PartlogsEntity> getAllPaginated(int page,int size, int direction, String... orderby);

    Page<PartlogsEntity> getAllPaginatedInSTimeRange(LocalDate fomDate, LocalDate toDate, int page,
                                         int size, int directionNumber, String... orderBy);

    Page<PartlogsEntity> getAllPaginatedInETimeRange(LocalDate fromDate, LocalDate toDate, int page,
                                                       int size, int directionNumber, String... orderBy);

    Page<PartlogsEntity> fullTextResearch(int page, int size, String keyword, int direction, String... orderBy);

    Page<PartlogsEntity> fulltextInRange(int page, int size, String keyword , int direction, LocalDate fromDate, LocalDate toDate, String... orderBy);

    int save();

}
