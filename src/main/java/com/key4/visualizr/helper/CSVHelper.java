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

            for(int i=0;i<records.size();i++) {

                if (isNumeric(records.get(i).get(0))) {
                    if (records.get(i).size() >= 6) {
                        errorEntities.add(new ErrorEntity(
                                Integer.parseInt(records.get(i).get(0)),
                                records.get(i).get(1),
                                records.get(i).get(2).isBlank() ? null : records.get(i).get(2),
                                records.get(i).get(3).isBlank() ? null : Integer.parseInt(records.get(i).get(3)),
                                records.get(i).get(4).isBlank() ? null : records.get(i).get(4),
                                records.get(i).get(5).isBlank() ? null : LocalDateTime.parse(records.get(i).get(5), DATE_TIME_FORMATTER))
                        );
                    }
                } else {
                    errorEntities.add(new ErrorEntity(
                            Integer.parseInt(records.get(i - 1).get(0)),
                            String.format("%s %s", records.get(i - 1).get(1), records.get(i).get(0)),
                            records.get(i).get(1),
                            Integer.parseInt(records.get(i).get(2)),
                            records.get(i).get(3),
                            LocalDateTime.parse(records.get(i).get(4),DATE_TIME_FORMATTER)
                    ));
                }
            }

            return errorEntities;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



//    public static List<ErrorEntity>  csvToErrorlog() {
//        try {
//            CSVParser parser = new CSVParser(new FileReader(ERROR_FILE_PATH),
//                    CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader().withAllowMissingColumnNames());
//            Iterator<CSVRecord> iterator = parser.iterator();
//
//            List<ErrorEntity> errorEntityList = new ArrayList<>();
//            ErrorEntity tempError = null;
//
//            while (iterator.hasNext()) {
//                CSVRecord record = iterator.next();
//
//                if (isNumeric(record.get(0))) {
//                    if (record.size() > 5) {
//                        ErrorEntity errorEntity = new ErrorEntity(
//                                Integer.parseInt(record.get(0)),
//                                record.get(1),
//                                record.get(2),
//                                record.get(3).isBlank() ? 0 : Integer.parseInt(record.get(3)),
//                                record.get(4),
//                                record.get(5).isEmpty() ? null : LocalDateTime.parse(record.get(5),DATE_TIME_FORMATTER)
//                        );
//                        errorEntityList.add(errorEntity);
//                    } else {
//                        tempError = new ErrorEntity();
//                        tempError.setCode(Integer.valueOf(record.get(0)));
//                        tempError.setDescription(record.get(1));
//                    }
//                } else {
//                    if (tempError != null) {
//                        ErrorEntity errorEntity = new ErrorEntity(
//                                tempError.getCode(),
//                                String.format("%s %s",tempError.getDescription(),record.get(0)),
//                                record.get(1),
//                                Integer.parseInt(record.get(2)),
//                                record.get(3),
//                                record.get(4).isEmpty() ? null : LocalDateTime.parse(record.get(4),DATE_TIME_FORMATTER));
//                        errorEntityList.add(errorEntity);
//                        tempError = null;
//                    }
//                }
//            }
//            return errorEntityList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    public static boolean isNumeric(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
