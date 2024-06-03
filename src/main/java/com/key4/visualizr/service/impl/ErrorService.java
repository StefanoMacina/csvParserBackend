package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.repository.ErrorRepository;
import com.key4.visualizr.service.IErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ErrorService implements IErrorService, CommandLineRunner {

    @Autowired
    ErrorRepository errorRepository;

    @Override
    public List<ErrorEntity> getAllErrors() {
        return errorRepository.findAll();
    }

    @Override
    public Page<ErrorEntity> getAllPaginated(int page, int size,
                                             int directionNumber, String... orderBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy));

        return errorRepository.findAll(pageRequest);
    }

    @Override
    public Page<ErrorEntity> fullTextResearch(int page, int size, String keyword,
                                              int directionNumber, String... orderBy) {
        PageRequest pageRequest = PageRequest.of(page,size,Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy);

        return errorRepository.search(String.valueOf(keyword),pageRequest);
    }

    @Override
    public Page<ErrorEntity> getErrorsInRange(LocalDate fromDate,
                                              LocalDate toDate,
                                              int directionNumber,
                                              int page, int size,
                                              String... orderBy
    ) {
        PageRequest pr = PageRequest.of(page,size,Sort.Direction.fromString(
                directionNumber != -1 ? "asc" : "desc"
        ),orderBy);
        return errorRepository.getErrorsByRange(fromDate, toDate, pr);
    }

    @Override
    public int save() {
        try {

          List<ErrorEntity> errorEntities = CSVHelper.csvToErrorlog();
          List<ErrorEntity> dbEntities = errorRepository.findAll();

          errorEntities.removeIf(dbEntities::contains);

          errorRepository.saveAll(errorEntities);
          return errorEntities.size();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }


    @Override
    public void run(String... args) throws Exception {
       // save();
    }
}
