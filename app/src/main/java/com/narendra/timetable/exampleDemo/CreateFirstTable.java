package com.narendra.timetable.exampleDemo;

import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Model.TimeTableModel;

public class CreateFirstTable {

    static void createTimeTable(){
        TimeTableModel model=GenerateModelData.generateData("T_3", 2, 5);
        //createTable(model); call this method
    }
}
