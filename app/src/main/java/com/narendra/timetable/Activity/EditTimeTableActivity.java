package com.narendra.timetable.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Adapter.RowNamesEditAdapter;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class EditTimeTableActivity extends AppCompatActivity {

    NavigationView navigationView;
    RecyclerView recyclerPeriod,recyclerDay,recyclerRowNamedEdit;
    TimeTableModel model1;
    boolean isNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_time_table);
        Bundle bundle=getIntent().getExtras();
        int timeTableId=bundle.getInt("TIMETABLEID");
        isNew=bundle.getBoolean("isNew");
        if(isNew){
            String timetableName=bundle.getString("TIMETABLENAME");
            int numberOfPeriods=bundle.getInt("NumberOfPeriods");
            int numberOfRows=bundle.getInt("NumberOfRows");
            model1= GenerateModelData.generateData(timetableName,numberOfRows,numberOfPeriods);
        }else {
            DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
            model1=helper.getTheModelFortheLoadingOfData(timeTableId);
            helper.loadTheDataForATimeTable(model1.getTimeTableName(),model1);
        }

        recyclerPeriod=findViewById(R.id.recyclerPeriod);
        recyclerDay=findViewById(R.id.recyclerDay);
        recyclerRowNamedEdit=findViewById(R.id.recyclerRowNames);
        RowNamesEditAdapter rowNamesAdapter=new RowNamesEditAdapter(this,model1.getRowNames());
        LinearLayoutManager rowNamesLayout=new LinearLayoutManager(this);
        rowNamesLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerRowNamedEdit.setAdapter(rowNamesAdapter);
        recyclerRowNamedEdit.setLayoutManager(rowNamesLayout);
        HashMap<String, ArrayList<RowModel>> timeTableValues=model1.getTimeTableValues();
        //
        ArrayList<PeriodTimeModel> period=model1.getPeriodTimes();
        GridLayoutManager periodLayoutManager=new GridLayoutManager(this,period.size()+1);
        PeriodAdapter periodAdapter=new PeriodAdapter(this,period,true);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
        //
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(this);
        DayAdapter dayAdapter=new DayAdapter(this,model1.getDays(),timeTableValues,model1.getRowNames(),true);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);
        /*
        DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        try {
            helper.updateTheTimeTable(model1);
            Toast.makeText(this, "TimeTable "+ model1.getTimeTableName()+" updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
         */
    }

    public void saveTheTimeTable(View view){
        DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        if(isNew){
            try {
                helper.createTable(model1);
            }catch (Exception e){
                Toast.makeText(this, "Sorry could not create timetable due to "+e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        try {
            helper.updateTheTimeTable(model1);
            Toast.makeText(this, "TimeTable "+ model1.getTimeTableName()+" updated successfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void logUpdatedvalues(View view){
        System.out.println(model1);
    }
}