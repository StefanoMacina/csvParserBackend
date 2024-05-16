package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.repository.ErrorRepository;
import com.key4.visualizr.service.IErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class ErrorService implements IErrorService {

    @Autowired
    ErrorRepository errorRepository;

    @Override
    public List<ErrorEntity> fetchAllErrors() {
        return errorRepository.findAll();
    }

    @Override
    public Page<ErrorEntity> fetchAllPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        return errorRepository.findAll(pageRequest);
    }

    @Override
    public void save() {
        try {
           List<ErrorEntity> errorEntities = CSVHelper.csvToErrorlog();
           errorRepository.saveAll(errorEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
