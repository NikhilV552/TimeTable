package com.narendra.timetable.Model;

public class Period {
    private String from;
    private String to;
    public Period(String from,String to){
        this.from=from;
        this.to=to;
    }
    public String getFrom(){
        return from;
    }
    public String getTo(){
        return to;
    }
    public void setFrom(String from){
        this.from=from;
    }
    public void setTo(String To){
        this.to=to;
    }

}
