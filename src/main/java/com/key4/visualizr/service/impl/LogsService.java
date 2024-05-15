package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.LogsEntity;
import com.key4.visualizr.repository.LogsRepository;
import com.key4.visualizr.service.ILogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class LogsService implements ILogsService {

    @Autowired
    LogsRepository logsRepository;

    @Override
    public List<LogsEntity> fetchAllLogs() {
        return logsRepository.findAll();
    }

    @Override
    public void save(MultipartFile file){

//        try {
//            List<LogsEntity> logsEntities = CSVHelper.csvToLog(file.getInputStream());
//            logsRepository.saveAll(logsEntities);
//        } catch (Exception e) {
//            throw new RuntimeException("fail to store csv data: " + e.getMessage());
//        }

    }

}
