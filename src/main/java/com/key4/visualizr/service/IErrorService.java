package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;

import java.util.List;

public interface IErrorService {

    List<ErrorEntity> fetchAllErrors();

    ErrorEntity save(ErrorEntity errorEntity);
}
