package com.key4.visualizr.service.impl;

import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.repository.PartogsRepository;
import com.key4.visualizr.service.IPartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartlogsService implements IPartlogsService {

    @Autowired
    PartogsRepository pl;

    @Override
    public List<PartlogsEntity> getAllLogs() {
        return pl.findAll();
    }

    @Override
    public Page<PartlogsEntity> getAllPaginatedAndSorted(int page, int size, String direction, String by) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(direction),by));
        return pl.findAll(pageRequest);
    }

    @Override
    public Page<PartlogsEntity> getAllPaginated(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"endTime"));
        return pl.findAll(pageRequest);
    }

    @Override
    public void save(){
        try {
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();
            pl.saveAll(logsEntities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
