package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.PartlogsEntity;

import java.util.List;

public interface ILogsService {

    List<PartlogsEntity> fetchAllLogs();

    void save();

}
