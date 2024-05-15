package com.key4.visualizr.service.impl;

import com.key4.visualizr.model.entity.LogsEntity;
import com.key4.visualizr.repository.LogsRepository;
import com.key4.visualizr.service.ILogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<LogsEntity> saveAll(List<LogsEntity> entities){
        return logsRepository.saveAll(entities);
    }

}
