package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.repository.LogsRepository;
import com.key4.visualizr.service.ILogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService implements ILogsService {

    @Autowired
    LogsRepository logsRepository;

    @Override
    public List<PartlogsEntity> fetchAllLogs() {
        return logsRepository.findAll();
    }

    @Override
    public Page<PartlogsEntity> fetchAllPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return logsRepository.findAll(pageRequest);
    }

    @Override
    public void save(){
        try {
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();
            logsRepository.saveAll(logsEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }

    }

}
