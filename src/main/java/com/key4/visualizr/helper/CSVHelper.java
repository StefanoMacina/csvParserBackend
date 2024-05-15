package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.LogsEntity;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.yaml.snakeyaml.tokens.ScalarToken;

public class CSVHelper {

    public static final String LOGS_FILE_PATH = "";

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
}