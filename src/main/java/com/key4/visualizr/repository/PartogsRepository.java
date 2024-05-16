package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartogsRepository extends JpaRepository<PartlogsEntity, Integer> {


}
