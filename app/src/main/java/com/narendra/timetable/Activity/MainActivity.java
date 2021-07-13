package com.narendra.timetable.Activity;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

public class MainActivity extends AppCompatActivity {
    GridView valueGV;

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
        TimeTableModel model1 = GenerateModelData.generateData("TIMETABLE 1",3,8);
        System.out.println(model1);
        model1.getPeriodTimes();
  }
}