package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IErrorService {

    List<ErrorEntity> getAllErrors();

    Page<ErrorEntity> getAllPaginated(int page, int size, int direction, String... orderBy);

    void save();

}
