package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PartogsRepository extends JpaRepository<PartlogsEntity, Integer> {

    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.description LIKE %:keyword% " +
            "OR e.code LIKE %:keyword% " +
            "OR e.state LIKE %:keyword%",
            nativeQuery = true
    )
    Page<PartlogsEntity> search(@Param("keyword") String keyword, Pageable pageable);


    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> getPartLogsBetweenStartTime(
            @Param("fromDate")LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
            );

    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.end_time >= :fromDate AND e.end_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> getPartLogsBetweenEndTime(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );

}
