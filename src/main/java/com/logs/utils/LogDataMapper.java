package com.logs.utils;

import com.logs.model.LogData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

public final class LogDataMapper {

    private LogDataMapper(){}

    private final static int INDEX_DATE = 0;
    private final static int INDEX_TIME = 1;
    private final static int INDEX_THREAD_ID = 2;
    private final static int INDEX_USER_CONTEXT = 3;
    private final static int INDEX_REQUEST = 4;

    public static List<LogData> getLogDataFromFile(String filePath) {
        List<String> linesFromTextFile = FileUtils.getLinesFromTextFile(filePath);

        return linesFromTextFile.stream()
                .map(LogDataMapper::lineToLogData)
                .collect(toList());
    }

    private static LogData lineToLogData(String line) {

        String[] lineSplitted = line.split(" ");

        LogData logData = new LogData();

        logData.setDate(convertDate(lineSplitted[INDEX_DATE]));
        logData.setTime(convertTime(lineSplitted[INDEX_TIME]));
        logData.setThreadId(lineSplitted[INDEX_THREAD_ID]);
        logData.setUserContext(lineSplitted[INDEX_USER_CONTEXT]);
        logData.setRequest(lineSplitted[INDEX_REQUEST]);
        logData.setDurationInMilliseconds(Long.valueOf( lineSplitted[lineSplitted.length - 1] ));

        return logData;
    }
    
    private static LocalDate convertDate(String date) {
        return LocalDate.parse(date);
    }

    private static LocalTime convertTime(String time) {
        return LocalTime.parse(time.replace(",", "."));
    }

}
