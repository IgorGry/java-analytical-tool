package com.qualityunit;

import java.time.LocalDate;

public class LineC extends Line {
    private LocalDate responseDate;
    private int waitingTimeInMinutes;

    public void setResponseDate(LocalDate responseDate) {
        this.responseDate = responseDate;
    }

    public void setWaitingTimeInMinutes(int waitingTimeInMinutes) {
        this.waitingTimeInMinutes = waitingTimeInMinutes;
    }

    public LocalDate getResponseDate() {
        return responseDate;
    }

    public int getWaitingTimeInMinutes() {
        return waitingTimeInMinutes;
    }

    @Override
    public String toString() {
        return
                "{" + super.toString() +
                        ", " + responseDate +
                        ", " + waitingTimeInMinutes +
                        "}" + "\n";

    }
}