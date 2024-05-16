package com.key4.visualizr.model.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity @NoArgsConstructor @Data
@Table(name = "partslogs")
public class PartlogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Log_index")
    private int log_index;

    @Column(name = "Bar length")
    private double bar_length;

    @Column(name = "Length")
    private double length;

    @Column(name = "Is a rest piece")
    private boolean is_a_rest_piece;

    @Column(name = "Job Code")
    private String job_Code;

    @Column(name = "Article")
    private String article;

    @Column(name = "Barcode")
    @Nullable
    private String barcode;

    @Column(name = "Profile code")
    private String profile_code;

    @Column(name = "Colour")
    @Nullable
    private String colour;

    @Column(name = "Start time")
    @Nullable
    private String start_time;

    @Column(name = "End time")
    @Nullable
    private String end_time;

    @Column(name = "Total span")
    @Nullable
    private String total_span;

    @Column(name = "Total producing span")
    @Nullable
    private String total_producing_span;

    @Column(name = "Overfeed")
    @Nullable
    private String overfeed;

    @Column(name = "Operator")
    private String operator;

    @Column(name = "Completed")
    private boolean completed;

    @Column(name = "Redone")
    private boolean redone;

    @Column(name = "Redone reason")
    @Nullable
    private String redone_reason;

    @Column(name = "Arming start time")
    @Nullable
    private String arming_start_time;

    @Column(name = "Arming end time")
    @Nullable
    private String arming_end_time;

    @Column(name = "Arming duration")
    @Nullable
    private String arming_duration;

    @Column(name = "Working start time")
    @Nullable
    private String working_start_time;

    @Column(name = "Working end time")
    @Nullable
    private String working_end_time;

    @Column(name = "Working duration")
    @Nullable
    private String working_duration;


    public PartlogsEntity(int log_index, double bar_length, double length,
                          boolean is_a_rest_piece, String job_Code,
                          String article, String barcode,
                          String profile_code, String colour,
                          String start_time, String end_time,
                          String total_span, String total_producing_span,
                          String overfeed, String operator, boolean completed,
                          boolean redone, String redone_reason,
                          String arming_start_time, String arming_end_time,
                          String arming_duration, String working_start_time,
                          String working_end_time, String working_duration
    ) {
        this.log_index = log_index;
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
        this.arming_start_time = arming_start_time;
        this.arming_end_time = arming_end_time;
        this.arming_duration = arming_duration;
        this.working_start_time = working_start_time;
        this.working_end_time = working_end_time;
        this.working_duration = working_duration;
    }
}


