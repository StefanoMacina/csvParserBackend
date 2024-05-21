package com.key4.visualizr.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Entity @NoArgsConstructor @Data
@Table(name = "partslogs")
public class PartlogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Log index")
    private int logIndex;

    @Column(name = "Bar length")
    private double bar_length;

    @Column(name = "Length")
    private double length;

    @Column(name = "Is a rest piece")
    private boolean restPiece;

    @Column(name = "job_code")
    private String jobCode;

    @Column(name = "Article")
    private String article;

    @Column(name = "bar_code")
    @Nullable
    private String barcode;

    @Column(name = "profile_code")
    private String profileCode;

    @Column(name = "color")
    @Nullable
    private String colour;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    @Nullable
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    @Nullable
    private LocalDateTime endTime;

    @Column(name = "total_span")
    @Nullable
    private String totalSpan;

    @Column(name = "Total producing span")
    @Nullable
    private String totalProducingSpan;

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
    private String redoneReason;

    @Column(name = "Arming start time")
    @Nullable
    private String armingStartTime;

    @Column(name = "Arming end time")
    @Nullable
    private String armingEndTime;

    @Column(name = "Arming duration")
    @Nullable
    private String armingDuration;

    @Column(name = "Working start time")
    @Nullable
    private String workingStartTime;

    @Column(name = "Working end time")
    @Nullable
    private String workingEndTime;

    @Column(name = "Working duration")
    @Nullable
    private String workingDuration;


    public PartlogsEntity(int logIndex, double bar_length, double length, boolean restPiece, String jobCode, String article,
                          @Nullable String barcode, String profileCode, @Nullable String colour, @Nullable LocalDateTime startTime,
                          @Nullable LocalDateTime endTime, @Nullable String totalSpan, @Nullable String totalProducingSpan,
                          @Nullable String overfeed, String operator, boolean completed, boolean redone, @Nullable String redoneReason,
                          @Nullable String armingStartTime, @Nullable String armingEndTime, @Nullable String armingDuration,
                          @Nullable String workingStartTime, @Nullable String workingEndTime, @Nullable String workingDuration) {
        this.logIndex = logIndex;
        this.bar_length = bar_length;
        this.length = length;
        this.restPiece = restPiece;
        this.jobCode = jobCode;
        this.article = article;
        this.barcode = barcode;
        this.profileCode = profileCode;
        this.colour = colour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSpan = totalSpan;
        this.totalProducingSpan = totalProducingSpan;
        this.overfeed = overfeed;
        this.operator = operator;
        this.completed = completed;
        this.redone = redone;
        this.redoneReason = redoneReason;
        this.armingStartTime = armingStartTime;
        this.armingEndTime = armingEndTime;
        this.armingDuration = armingDuration;
        this.workingStartTime = workingStartTime;
        this.workingEndTime = workingEndTime;
        this.workingDuration = workingDuration;
    }
}


