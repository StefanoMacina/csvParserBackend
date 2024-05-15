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

    public LogsEntity(int index, double bar_length, double length, boolean is_a_rest_piece, String job_Code, String article, String barcode, String profile_code, String colour, LocalDateTime start_time, LocalDateTime end_time, double total_span, double total_producing_span, double overfeed, String operator, boolean completed, boolean redone, String redone_reason) {
        this.index = index;
        this.bar_length = bar_length;
        this.length = length;
        this.is_a_rest_piece = is_a_rest_piece;
        this.job_Code = job_Code;
        this.article = article;
        this.barcode = barcode;
        this.profile_code = profile_code;
        this.colour = colour;
        this.start_time = start_time;
        this.end_time = end_time;
        this.total_span = total_span;
        this.total_producing_span = total_producing_span;
        this.overfeed = overfeed;
        this.operator = operator;
        this.completed = completed;
        this.redone = redone;
        this.redone_reason = redone_reason;
    }
}
