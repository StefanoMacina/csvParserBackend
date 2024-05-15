package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IErrorService {

    List<ErrorEntity> fetchAllErrors();

    void save();

}
