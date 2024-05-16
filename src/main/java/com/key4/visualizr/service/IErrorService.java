package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface IErrorService {

    List<ErrorEntity> getAllErrors();

    Page<ErrorEntity> getAllPaginated(int page, int size);

    void save();

}
