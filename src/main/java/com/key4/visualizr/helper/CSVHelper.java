package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.PartlogsEntity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Locked;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.cglib.core.Local;

public class CSVHelper {

    public static final String LOGS_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Error_log.csv";
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
    final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static List<PartlogsEntity> csvToPartlog() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(LOGS_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withDelimiter(';')
                        .withIgnoreEmptyLines()
                        .withAllowMissingColumnNames()
                        .withTrim());
        )
        {
            List<PartlogsEntity> logsEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                int log_index = Integer.parseInt(csvRecord.get("Index"));
                double bar_length = Double.parseDouble(csvRecord.get("Bar length"));
                double length = Double.parseDouble(csvRecord.get("Length"));
                boolean restPiece = Boolean.parseBoolean(csvRecord.get("Is a rest piece"));
                String jobCode = csvRecord.get("Job Code");
                String article = csvRecord.get("Article");
                String barcode = csvRecord.get("Barcode");
                String profileCode = csvRecord.get("Profile code");
                String colour = csvRecord.get("Colour");
                LocalDateTime startTime = csvRecord.get("Start time").isBlank() ? null : LocalDateTime.parse(csvRecord.get("Start time"),DATE_TIME_FORMATTER) ;
                LocalDateTime endTime = csvRecord.get("End time").isBlank() ? null : LocalDateTime.parse(csvRecord.get("End time"), DATE_TIME_FORMATTER);
                String totalSpan = csvRecord.get("Total span");
                String totalProducingSpan = csvRecord.get("Total producing span");
                String overfeed = csvRecord.get("Overfeed");
                String operator = csvRecord.get("Operator");
                boolean completed = Boolean.parseBoolean(csvRecord.get("Completed"));
                boolean redone = Boolean.parseBoolean(csvRecord.get("Redone"));
                String redoneReason = csvRecord.get("Redone reason");
                String arming_start = csvRecord.get("Arming start time");
                String arming_end = csvRecord.get("Arming end time");
                String arming_duration = csvRecord.get("Arming duration");
                String working_start = csvRecord.get("Working start time");
                String working_end = csvRecord.get("Working end time");
                String working_duration = csvRecord.get("Working duration");

                PartlogsEntity logEntity = new PartlogsEntity(
                        log_index,
                        bar_length,
                        length,
                        restPiece,
                        jobCode,
                        article,
                        barcode,
                        profileCode,
                        colour,
                        startTime,
                        endTime,
                        totalSpan.isBlank() ? "" : totalSpan,
                        totalProducingSpan.isBlank() ? "" : totalProducingSpan,
                        overfeed.isBlank() ? "" : overfeed,
                        operator,
                        completed,
                        redone,
                        redoneReason,
                        arming_start.isBlank() ? "" : arming_start,
                        arming_end.isBlank() ? "" : arming_end,
                        arming_duration.isBlank() ? "" : arming_duration,
                        working_start.isBlank() ? "" : working_start,
                        working_end.isBlank() ? "" : working_end,
                        working_duration.isBlank() ? "" : working_duration
                );
                logsEntities.add(logEntity);
            }

            return logsEntities;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("error parsing partslog");
        }

    }

    public static List<ErrorEntity> csvToErrorlog(){
        try{
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withAllowMissingColumnNames()
                    .withDelimiter(';');
            CSVParser parser = new CSVParser(new FileReader(ERROR_FILE_PATH),csvFormat);

            List<CSVRecord> records = parser.getRecords();

            List<ErrorEntity> errorEntities = new ArrayList<>();

            for(int i=0;i<records.size()-1;i++) {

                    CSVRecord record = records.get(i);
                    CSVRecord nextRecord = records.get(i+1);

                    if(isNumeric(record.get(0))){
                        if(record.get(3).isEmpty()){
                           errorEntities.add(
                                   new ErrorEntity(
                                           Integer.parseInt(record.get(0)),
                                           String.format("%s %s",record.get(1), nextRecord.get(0)),
                                           nextRecord.get(1),
                                           Integer.parseInt(nextRecord.get(2)),
                                           nextRecord.get(3),
                                           LocalDateTime.parse(nextRecord.get(4),DATE_TIME_FORMATTER)
                                   )
                           );
                        } else {
                            errorEntities.add(
                                    new ErrorEntity(
                                            Integer.parseInt(record.get(0)),
                                            record.get(1),
                                            record.get(2),
                                            Integer.parseInt(record.get(3)),
                                            record.get(4),
                                            LocalDateTime.parse(record.get(5),DATE_TIME_FORMATTER)
                                    )
                            );
                        }
                    } else {
                        i += 1;
                    }
            }
            return errorEntities;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNumeric(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
