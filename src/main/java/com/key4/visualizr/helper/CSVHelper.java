package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.LogsEntity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    public static final String LOGS_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "C:\\Users\\macina\\Desktop\\i4Error_log.csv";

    public static List<LogsEntity> csvToLog() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(LOGS_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        )
        {
            List<LogsEntity> logsEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                LogsEntity logEntity = new LogsEntity(
                        Integer.parseInt(csvRecord.get("Id")),
                        Double.parseDouble(csvRecord.get("Bar_length")),
                        Double.parseDouble(csvRecord.get("Length")),
                        Boolean.parseBoolean(csvRecord.get("Is_a_rest_peace")),
                        csvRecord.get("Job_code"),
                        csvRecord.get("Article"),
                        csvRecord.get("Barcode"),
                        csvRecord.get("Profile_code"),
                        csvRecord.get("Colour"),
                        LocalDateTime.parse(csvRecord.get("Start_time")),
                        LocalDateTime.parse(csvRecord.get("End_time")),
                        Double.parseDouble(csvRecord.get("Total_span")),
                        Double.parseDouble(csvRecord.get("Total_producing_span")),
                        Double.parseDouble(csvRecord.get("Overfeed")),
                        csvRecord.get("Operator"),
                        Boolean.parseBoolean(csvRecord.get("Completed")),
                        Boolean.parseBoolean(csvRecord.get("Redone")),
                        csvRecord.get("Redone_reasone")
                );

                logsEntities.add(logEntity);
            }

            return logsEntities;
        } catch (IOException e){
            throw new RuntimeException("error parsing logs");
        }
    }

    public static List<ErrorEntity> csvToError() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(ERROR_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim());
        )
        {
            List<ErrorEntity> errorEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                ErrorEntity errorEntity = new ErrorEntity(
                       Integer.parseInt(csvRecord.get("Code")),
                        csvRecord.get("Description"),
                        LocalTime.parse(csvRecord.get("Duration")),
                        Integer.parseInt(csvRecord.get("Occurrences")),
                        csvRecord.get("State"),
                        LocalDateTime.parse(csvRecord.get("date"))
                );

                errorEntities.add(errorEntity);
            }
            return errorEntities;

        } catch (IOException e){
            throw new RuntimeException("error parsing errors");
        }
    }
}