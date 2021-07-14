package com.narendra.timetable.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerPeriod;
    RecyclerView recyclerDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        valueGV=findViewById(R.id.gridView);
//        ArrayList<> contentArrayList=new ArrayList<Content>();
//        contentArrayList.add(new Content("SSC"));
//        contentArrayList.add(new Content("CGV"));
//        contentArrayList.add(new Content("WT"));
//        contentArrayList.add(new Content("AJAVA"));
//        contentArrayList.add(new Content("Maths"));
//        contentArrayList.add(new Content("MAD"));
//        contentArrayList.add(new Content("CGVL"));
//        contentArrayList.add(new Content("SSOSL"));
//        contentArrayList.add(new Content("MBS"));
//        contentArrayList.add(new Content("KJB"));
//        contentArrayList.add(new Content("SHR"));
//        contentArrayList.add(new Content("Netravathy"));
//        contentArrayList.add(new Content("KMR"));
//        contentArrayList.add(new Content("Sruthiba"));
//        contentArrayList.add(new Content("Sruthiba"));
//        contentArrayList.add(new Content("Nethravathy"));
//        TimeTableAdapter adapter=new TimeTableAdapter(this,contentArrayList);
//        valueGV.setAdapter(adapter);
            recyclerPeriod=findViewById(R.id.recyclerPeriod);
            recyclerDay=findViewById(R.id.recyclerDay);

        TimeTableModel model1 = GenerateModelData.generateData("TIMETABLE 1",3,8);
        System.out.println(model1);
        PeriodTimeModel initial=new PeriodTimeModel(new Time(00000000),new Time(00000000));
        ArrayList<PeriodTimeModel> period=model1.getPeriodTimes();
        HashMap<String,ArrayList<RowModel>> timeTableValues=model1.getTimeTableValues();
        period.add(0,initial);
        System.out.println(period.size());
        GridLayoutManager periodLayoutManager=new GridLayoutManager(this,period.size());
        PeriodAdapter periodAdapter=new PeriodAdapter(this,period);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(this);
        DayAdapter dayAdapter=new DayAdapter(this,model1.getDays(),timeTableValues);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);
    }
}