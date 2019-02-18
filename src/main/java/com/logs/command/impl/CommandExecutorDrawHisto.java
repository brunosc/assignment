package com.logs.command.impl;

import com.logs.command.CommandExecutor;
import com.logs.model.LogData;
import com.logs.utils.ParameterConstants;
import com.logs.utils.LogDataMapper;
import com.logs.utils.Logger;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandExecutorDrawHisto implements CommandExecutor {

    @Override
    public void execute(String[] args) {

        List<LogData> logDataList = LogDataMapper.getLogDataFromFile(args[ParameterConstants.INDEX_FILE]);

        Map<LocalDate, List<LogData>> groupedByDate = groupByDate(logDataList);

        groupedByDate.entrySet().forEach(this::groupByHourAndDraw);
    }

    private Map<LocalDate, List<LogData>> groupByDate(List<LogData> logDataList) {
        return logDataList.stream().collect(Collectors.groupingBy(LogData::getDate));
    }

    private void groupByHourAndDraw(Map.Entry<LocalDate, List<LogData>> entry) {
        Map<Integer, List<LogData>> groupedByHour = entry.getValue().stream().collect(Collectors.groupingBy(LogData::getHourOfDay));
        drawHistogram(entry.getKey(), groupedByHour);
    }

    private void drawHistogram(LocalDate date, Map<Integer, List<LogData>> groupedByHour) {
        Logger.info("******************** Histogram of %s ********************", date);
        groupedByHour.forEach(this::drawHistogramHourly);
        Logger.info("*****************************************************************\n");
    }

    private void drawHistogramHourly(Integer hour, List<LogData> logDataList) {
        String hourInString = String.format("%02d", hour);
        String bars = String.join("", Collections.nCopies(logDataList.size(), "*"));

        Logger.info(hourInString.concat(" : ").concat(bars));
    }

}
