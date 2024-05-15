package com.key4.visualizr.helper;

import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.LogsEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVHelper {

    public static String type = "text/csv";

    public static String[] logsHeader = {""};

    public static String[] errorsheader = {""};

    public static List<LogsEntity> csvToLog(InputStream is) {
        try(
                BufferedReader fr = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fr, CSVFormat.DEFAULT
                        .withDelimiter(';')
                        .withFirstRecordAsHeader()
                        .withTrim())) {

            List<LogsEntity> csvLogs = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for(var csvRecord : csvRecords){

                LogsEntity logsEntity = new LogsEntity(
                            Integer.parseInt(csvRecord.get("Index")),
                            Double.parseDouble(csvRecord.get("Bar_length")),
                            Double.parseDouble(csvRecord.get("Length")),
                            Boolean.parseBoolean(csvRecord.get("Is_a_rest_piece")),
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
                            csvRecord.get("Redone_reason"));

            }

        } catch (IOException e){
            throw new RuntimeException("Error parsing csv file");
        }
    }

    public static List<ErrorEntity> csvToError(InputStream is) {

    }
}
