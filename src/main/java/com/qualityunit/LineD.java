package com.qualityunit;

import java.time.LocalDate;

public class LineD extends Line {
    private LocalDate dateFrom;
    private LocalDate dateTo;

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    @Override
    public String toString() {
        return
                "{" + super.toString() +
                        ", " + dateFrom +
                        "-" + dateTo +
                        "}";

    }
}
