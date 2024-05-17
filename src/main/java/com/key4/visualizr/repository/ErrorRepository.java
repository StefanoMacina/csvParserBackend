package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ErrorRepository extends JpaRepository<ErrorEntity, Integer> {

    @Query(value = "SELECT * FROM errorlogs e WHERE e.description LIKE %:keyword%",
            nativeQuery = true
    )
    Page<ErrorEntity> search(@Param("keyword") String keyword, Pageable pageable);
}

