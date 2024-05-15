package com.key4.visualizr.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @NoArgsConstructor @Data
@Table(name = "logs")
public class LogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int index;
    private double bar_length;
    private double length;
    private boolean is_a_rest_piece;
    private String job_Code;
    private String article;
    private String barcode;
    private String profile_code;
    private String colour;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private double total_span;
    private double total_producing_span;
    private double overfeed;
    private String operator;
    private boolean completed;
    private boolean redone;
    private String redone_reason;

}
