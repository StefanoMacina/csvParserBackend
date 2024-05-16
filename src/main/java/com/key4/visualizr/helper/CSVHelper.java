package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.LogsEntity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    public static final String LOGS_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Error_log.csv";
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");
    final static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static List<LogsEntity> csvToLog() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(LOGS_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withIgnoreSurroundingSpaces()
                        .withNullString("")
                        .withDelimiter(';')
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase())
                )
        {
            List<LogsEntity> logsEntities = new ArrayList<>();
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
                        LocalDateTime startTime = LocalDateTime.parse(csvRecord.get("Start time"),formatter);
                        LocalDateTime endTime = LocalDateTime.parse(csvRecord.get("End time"), formatter);
                        double totalSpan = Double.parseDouble(csvRecord.get("Total span"));
                        double totalProducingSpan = Double.parseDouble(csvRecord.get("Total producing span"));
                        double overfeed = Double.parseDouble(csvRecord.get("Overfeed"));
                        String operator = csvRecord.get("Operator");
                        boolean completed = Boolean.parseBoolean(csvRecord.get("Completed"));
                        boolean redone = Boolean.parseBoolean(csvRecord.get("Redone"));
                        String redoneReason = csvRecord.get("Redone reason");
                        LocalDateTime arming_start = LocalDateTime.parse(csvRecord.get("Arming start time"), formatter);
                        LocalDateTime arming_end = LocalDateTime.parse(csvRecord.get("Arming end time"), formatter);
                        LocalTime arming_duration = LocalTime.parse(csvRecord.get("Arming duration"), timeFormatter);
                        LocalDateTime working_start = LocalDateTime.parse(csvRecord.get("Working start time"), formatter);
                        LocalDateTime working_end = LocalDateTime.parse(csvRecord.get("Working end time"), formatter);
                        LocalTime working_duration = LocalTime.parse(csvRecord.get("Working duration"), timeFormatter);

                    LogsEntity logEntity = new LogsEntity(
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
                    totalSpan,
                    totalProducingSpan,
                    overfeed,
                    operator,
                    completed,
                    redone,
                    redoneReason,
                    arming_start,
                    arming_end,
                    arming_duration,
                    working_start,
                    working_end,
                    working_duration
                );
                logsEntities.add(logEntity);
            }

            return logsEntities;
        } catch (IOException e){
            e.printStackTrace();
           throw new RuntimeException("error parsing partslog");
        }

    }

    public static List<ErrorEntity> csvToError() {


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