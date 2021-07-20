package com.narendra.timetable.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*DatabaseTimeTableHelper timeTableHelper=new DatabaseTimeTableHelper(SplashActivity.this);
                TimeTableModel model= GenerateModelData.generateData("T_1",3,9);
                timeTableHelper.createTable(model);*/
//                int len=helper.getAllTimeTableNamesPresent().size();
//                if(len!=0)
//                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                 else
                    startActivity(new Intent(SplashActivity.this,CreateActivity.class));

                /*Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);*/
                finish();
            }
        },3000);
    }
}