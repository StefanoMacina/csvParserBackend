package com.key4.visualizr.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pojo {
    @Nullable
    private Integer page = 0;
    @Nullable
    private Integer size = 20;
    @Nullable
    private String orderBy = "startTime";
    @Nullable
    private Integer dir = -1;
    @Nullable
    private String globalfilter;
    @Nullable
    private String range = "endTime";
    @Nullable
    private LocalDate fromDate = LocalDate.now()
            .minusWeeks(10);
    @Nullable
    private LocalDate toDate = LocalDate.now();

}
