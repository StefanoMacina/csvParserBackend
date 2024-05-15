package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.LogsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILogsService {

    List<LogsEntity> fetchAllLogs();

    void save(MultipartFile file);

}
