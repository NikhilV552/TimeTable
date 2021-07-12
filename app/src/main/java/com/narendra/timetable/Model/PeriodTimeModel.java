package com.narendra.timetable.Model;

import java.sql.Time;

public class PeriodTimeModel {

    private Time from;
    private Time to;
    public PeriodTimeModel(Time from, Time to) {
        super();
        this.from = from;
        this.to = to;
    }
    public Time getFrom() {
        return from;
    }
    public void setFrom(Time from) {
        this.from = from;
    }
    public Time getTo() {
        return to;
    }
    public void setTo(Time to) {
        this.to = to;
    }
    @Override
    public String toString() {
        return "PeriodTimeModel [from=" + from.toString() + ", to=" + to.toString() + "]";
    }
}
