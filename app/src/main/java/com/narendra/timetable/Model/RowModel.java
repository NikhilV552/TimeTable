package com.narendra.timetable.Model;

import java.util.ArrayList;

public class RowModel {
    private ArrayList<String> rowValues;

    public ArrayList<String> getRowValues() {
        return rowValues;
    }

    public void setRowValues(ArrayList<String> rowValues) {
        this.rowValues = rowValues;
    }

    public RowModel(ArrayList<String> rowValues) {
        super();
        this.rowValues = rowValues;
    }

    public RowModel(int numberOfRows) {
        this.rowValues=new ArrayList<String>();
        for(int i=0;i<numberOfRows;i++) {
            this.rowValues.add("");
        }
    }

    @Override
    public String toString() {
        return "RowModel [rowValues=" + rowValues + "]";
    }
}
