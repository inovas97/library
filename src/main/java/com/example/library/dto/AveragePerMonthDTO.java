package com.example.library.dto;

import java.util.ArrayList;
import java.util.List;

public class AveragePerMonthDTO {
    public int bookId;
    List<MonthAverage> monthAverage;

    public AveragePerMonthDTO() {
        monthAverage = List.of();
    }

    public AveragePerMonthDTO(int bookId, List<MonthAverage> monthAverage) {
        this.bookId = bookId;
        this.monthAverage = new ArrayList<>(monthAverage);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public List<MonthAverage> getMonthAverage() {
        return monthAverage;
    }

    public void setMonthAverage(List<MonthAverage> monthAverage) {
        this.monthAverage = new ArrayList<>(monthAverage);
    }
}
