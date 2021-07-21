package com.narendra.timetable.exampleDemo;

import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class GenerateModelData {

    public static TimeTableModel generateData(String tableName, int numberOfRows, int numberOfPeriods) {
        String[] days= {"MONDAY","TUESDAY","WEDNESDAY","THURSDAY","FRIDAY","SATURDAY"};
        TimeTableModel timeTableModel=null;
        int k=0;
        HashMap<String,ArrayList<RowModel>> timeTableValues=new HashMap<String,ArrayList<RowModel>>() ;
        for(int i=0;i<6;i++) {
            ArrayList<RowModel> rowValues=new ArrayList<RowModel>();
            for(int j=0;j<numberOfPeriods;j++) {
                rowValues.add(new RowModel(numberOfRows));
            }

            timeTableValues.put(days[i], rowValues);
            /*for(int j=0;j<numberOfPeriods;j++) {
                for(int r=0;r<numberOfRows;r++) {
                    rowValues.get(j).getRowValues().set(r, tableName+" ROW D"+i+" P"+j+" RW"+r);
                }
            }*/

        }
        ArrayList<String> rowNames=new ArrayList<String>();
        for(int i=0;i<numberOfRows;i++) {
            rowNames.add("ROW "+(i+1));
        }
        ArrayList<PeriodTimeModel> periodTimeValues=new ArrayList<PeriodTimeModel>();
        for(int i=8;i<8+numberOfPeriods;i++) {
            periodTimeValues.add(new PeriodTimeModel(new Time(i,0,0),new Time(i+1,0,0)));
        }
        timeTableModel=new TimeTableModel(1,tableName,numberOfPeriods,numberOfRows,rowNames,periodTimeValues,timeTableValues);

        return timeTableModel;
    }

}
