package com.key4.visualizr.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity @NoArgsConstructor @Data
@Table(name = "logs")
public class ErrorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int code;
    private String description;
    private LocalTime duration;
    private int occurences;
    private String state;
    private LocalDateTime date;

}
