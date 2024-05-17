package com.key4.visualizr.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor @Data
@Table(name = "errorlogs")
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "description",length = 500)
    private String description;

    @Column(name = "duration (hh:mm:Ss)")
    @Nullable
    private String duration;

    @Column(name = "occurrences")
    @Nullable
    private String occurences;

    @Column(name = "state")
    private String state;

    @Column(name = "date")
    private String date;

    @Column(name = "txt")
    private String txt;

    public ErrorEntity(String code, String description,
                       String duration, String occurences,
                       String state, String date,
                       String txt) {
        this.code = code;
        this.description = description;
        this.duration = duration;
        this.occurences = occurences;
        this.state = state;
        this.date = date;
        this.txt = txt;
    }

}
