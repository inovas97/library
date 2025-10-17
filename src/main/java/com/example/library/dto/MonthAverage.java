package com.example.library.dto;

public class MonthAverage {
    String year;
    String month;
    Double average;

    public MonthAverage() {}

    public MonthAverage(String year, String month, Double average) {
        this.year = year;
        this.month = month;
        this.average = average;
    }

    public Double getAverage() {
        return average;
    }
    public void setAverage(Double average) {
        this.average = average;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
