package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.LogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<LogsEntity, Integer> {



}
