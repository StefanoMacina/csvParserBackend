package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IErrorService {

    List<ErrorEntity> getAllErrors();

    Page<ErrorEntity> getAllPaginated(int page, int size, int direction, String... orderBy);

    Page<ErrorEntity> fullTextResearch(int page, int size, String keyword ,int direction, String... orderBy);

    void save();

}
