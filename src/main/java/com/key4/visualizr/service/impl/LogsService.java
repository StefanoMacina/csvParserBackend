package com.key4.visualizr.service.impl;

import com.key4.visualizr.model.entity.LogsEntity;
import com.key4.visualizr.service.ILogsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService implements ILogsService {
    @Override
    public List<LogsEntity> fetchAllLogs() {
        return List.of();
    }

    @Override
    public void save(LogsEntity logsEntity) {

    }
}
