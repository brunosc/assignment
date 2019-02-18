package com.logs.command.impl;

import com.logs.command.CommandExecutor;
import com.logs.exception.ParameterValidationException;
import com.logs.model.AverageRequestDuration;
import com.logs.model.LogData;
import com.logs.utils.ParameterConstants;
import com.logs.utils.LogDataMapper;
import com.logs.utils.Logger;

import java.util.List;
import java.util.Map;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class CommandExecutorPrintTopN implements CommandExecutor {

    @Override
    public void execute(String[] args) {

        Long numberOfRecords = getNumberOfRecords(args[ParameterConstants.INDEX_N]);
        List<LogData> logDataList = LogDataMapper.getLogDataFromFile(args[ParameterConstants.INDEX_FILE]);

        List<AverageRequestDuration> averages = calculateAverages(logDataList);

        Logger.info("******************** Printing top %s ********************", numberOfRecords);

        averages.sort(comparing(AverageRequestDuration::getAverage).reversed());
        averages.stream().limit(numberOfRecords).forEach(this::printAvg);

        Logger.info("*********************************************************");
    }

    private List<AverageRequestDuration> calculateAverages(List<LogData> logs) {
        Map<String, List<LogData>> logDataGroupedByResource = logs.stream().collect(groupingBy(LogData::getGroupedResourceName));
        return logDataGroupedByResource.entrySet().stream().map(this::calculateAverage).collect(toList());
    }

    private AverageRequestDuration calculateAverage(Map.Entry<String, List<LogData>> entry) {
        long durationSum = entry.getValue().stream().mapToLong(LogData::getDurationInMilliseconds).sum();
        long average = durationSum / entry.getValue().size();

        return new AverageRequestDuration(entry.getKey(), average);
    }

    private void printAvg(AverageRequestDuration average) {
        Logger.info("* " + average.toString());
    }

    private Long getNumberOfRecords(String value) {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ParameterValidationException(String.format("Error on converting \"%s\" into a number", value));
        }
    }
}
