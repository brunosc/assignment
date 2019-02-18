package com.logs.model;

public class AverageRequestDuration {

    private String resource;
    private Long average;

    public AverageRequestDuration(String resource, Long average) {
        this.resource = resource;
        this.average = average;
    }

    public String getResource() {
        return resource;
    }

    public Long getAverage() {
        return average;
    }

    @Override
    public String toString() {
        return String.format("Average: %d - Resouce: %s", average, resource);
    }
}
