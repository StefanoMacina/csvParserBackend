package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.LogsEntity;

import java.util.List;

public interface ILogsService {

    List<LogsEntity> fetchAllLogs();

    List<LogsEntity> saveAll(List<LogsEntity> logsEntities);

}
