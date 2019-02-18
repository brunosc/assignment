package com.logs.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogData {

    private LocalDate date;
    private LocalTime time;
    private String threadId;
    private String userContext;
    private String request;
    private Long durationInMilliseconds;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getUserContext() {
        return userContext;
    }

    public void setUserContext(String userContext) {
        this.userContext = userContext;
    }

    public String getRequest() {
        return request;
    }

    public String getGroupedResourceName() {

        int indexQuery = request.indexOf("?");

        if (indexQuery >= 0) {
            return request.substring(0, indexQuery);
        }

        return request;
    }

    public int getHourOfDay() {
        return time.getHour();
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Long getDurationInMilliseconds() {
        return durationInMilliseconds;
    }

    public void setDurationInMilliseconds(Long durationInMilliseconds) {
        this.durationInMilliseconds = durationInMilliseconds;
    }
}
