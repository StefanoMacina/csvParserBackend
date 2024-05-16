package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IPartlogsService {

    List<PartlogsEntity> getAllLogs();

    Page<PartlogsEntity> getAllPaginated(int page,int size);

    void save();

}
