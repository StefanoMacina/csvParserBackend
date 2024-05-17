package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartogsRepository extends JpaRepository<PartlogsEntity, Integer> {

    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.description LIKE %:keyword% " +
            "OR e.code LIKE %:keyword% " +
            "OR e.state LIKE %:keyword%",
            nativeQuery = true
    )
    Page<ErrorEntity> search(@Param("keyword") String keyword, Pageable pageable);

}
