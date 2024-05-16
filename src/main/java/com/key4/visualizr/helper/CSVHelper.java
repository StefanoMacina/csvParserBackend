package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.PartlogsEntity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    public static final String LOGS_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Error_log.csv";
    final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
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
                        String startTime = csvRecord.get("Start time");
                        String endTime = csvRecord.get("End time");
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
                        String working_duration =csvRecord.get("Working duration");

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
                        startTime.isBlank() ? "" : startTime,
                        endTime.isBlank() ? "" : endTime,
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
        } catch (IOException e){
            e.printStackTrace();
           throw new RuntimeException("error parsing partslog");
        }

    }

    public static List<ErrorEntity> csvToErrorlog() {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(ERROR_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withDelimiter(';')
                        .withIgnoreEmptyLines()
                        .withAllowMissingColumnNames()
                        .withTrim());
        )
        {
            List<ErrorEntity> errorEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                String code = csvRecord.get("Code");
                String description = csvRecord.get("Description");
                String duration = csvRecord.get("Duration (hh:mm:Ss)");
                String occurences = csvRecord.get("Occurences");
                String state = csvRecord.get("State");
                String date = csvRecord.get("Date");
                String emptyColumnAlarm = csvRecord.get("");


                ErrorEntity errorEntity = new ErrorEntity(
                        isNumeric(code) ? code : "-1",
                        isNumeric(code) ? description : code,
                        isNumeric(code) ? duration : description,
                        isNumeric(code) ? occurences : duration,
                        isNumeric(code) ? state : occurences,
                        isNumeric(code) ? date : state,
                        isNumeric(code) ? emptyColumnAlarm : ""
                 );

                errorEntities.add(errorEntity);
            }
            return errorEntities;
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("error parsing record");
        }
    }

    public static boolean isNumeric(String num)
    {
        try{
            int n = Integer.parseInt(num);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}