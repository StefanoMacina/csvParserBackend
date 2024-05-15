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

    public static final String LOGS_FILE_PATH = "/home/quark/Desktop/i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "/home/quark/Desktop/i4Error_log.csv";

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

                );

                logsEntities.add(logEntity);
            }

            return logsEntities;
        } catch (IOException e){
            throw new RuntimeException("error parsing partslog");
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
            throw new RuntimeException("error parsing record");
        }
    }
}