package com.narendra.timetable.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableModel {
    private String[] days= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
    private int timeTableId;
    private String timeTableName;
    private int numberOfperiods;
    private int numberOfRows;
    private ArrayList<String> rowNames;
    private ArrayList<PeriodTimeModel> periodTimes;
    private HashMap<String,ArrayList<RowModel>> timeTableValues;
    public TimeTableModel(int timeTableId, String timeTableName, int numberOfperiods, int numberOfRows,
                          ArrayList<String> rowNames, ArrayList<PeriodTimeModel> periodTimes,
                          HashMap<String, ArrayList<RowModel>> timeTableValues) {
        super();
        this.timeTableId = timeTableId;
        this.timeTableName = timeTableName;
        this.numberOfperiods = numberOfperiods;
        this.numberOfRows = numberOfRows;
        this.rowNames = rowNames;
        this.periodTimes = periodTimes;
        this.timeTableValues = timeTableValues;
    }
    public int getTimeTableId() {
        return timeTableId;
    }
    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }
    public String getTimeTableName() {
        return timeTableName;
    }
    public void setTimeTableName(String timeTableName) {
        this.timeTableName = timeTableName;
    }
    public int getNumberOfperiods() {
        return numberOfperiods;
    }
    public void setNumberOfperiods(int numberOfperiods) {
        this.numberOfperiods = numberOfperiods;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }
    public String[] getDays(){ return days;}
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }
    public ArrayList<String> getRowNames() {
        return rowNames;
    }
    public void setRowNames(ArrayList<String> rowNames) {
        this.rowNames = rowNames;
    }
    public ArrayList<PeriodTimeModel> getPeriodTimes() {
        return periodTimes;
    }
    public void setPeriodTimes(ArrayList<PeriodTimeModel> periodTimes) {
        this.periodTimes = periodTimes;
    }
    public HashMap<String, ArrayList<RowModel>> getTimeTableValues() {
        return timeTableValues;
    }
    public void setTimeTableValues(HashMap<String, ArrayList<RowModel>> timeTableValues) {
        this.timeTableValues = timeTableValues;
    }
    @Override
    public String toString() {
        String result="";
        for(int i=0;i<numberOfperiods;i++) {
            System.out.printf("%10s ","PERIOD"+(i+1));
        }
        System.out.println();
        for(int i=0;i<numberOfperiods;i++) {
            System.out.printf("%10s ",periodTimes.get(i).getFrom().toString());
        }
        System.out.println();
        for(int i=0;i<numberOfperiods;i++) {
            System.out.printf("%10s ",periodTimes.get(i).getTo().toString());
        }
        System.out.println("\n"+rowNames);
        for(int i=0;i<6;i++) {
            ArrayList<RowModel> rowModel=timeTableValues.get(days[i]);
            System.out.println(days[i]+rowModel);
            for(int j=0;j<numberOfperiods;j++) {
                RowModel rowModelValue=rowModel.get(j);
                for(int k=0;k<numberOfRows;k++) {
                    System.out.print(rowModelValue.getRowValues().get(k)+",");
                }
                System.out.println();
            }
        }
        return result;
    }
}
