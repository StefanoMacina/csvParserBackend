package com.key4.visualizr.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor @Data
@Table(name = "errorlogs")
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    @Nullable
    private String duration;

    @Column(name = "occurences")
    @Nullable
    private Integer occurences;

    @Column(name = "state")
    private String state;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "date")
    private LocalDateTime date;


    public ErrorEntity(Integer code, String description,
                       String duration, Integer occurences,
                       String state, LocalDateTime date
    ) {
        this.code = code;
        this.description = description;
        this.duration = duration;
        this.occurences = occurences;
        this.state = state;
        this.date = date;
    }

}
