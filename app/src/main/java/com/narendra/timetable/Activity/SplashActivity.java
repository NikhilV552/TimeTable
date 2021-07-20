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
        setContentView(R.layout.activity_splash);
        DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        int len=helper.getAllTimeTableNamesPresent().size();
        new Handler().postDelayed(() -> {
            if(len!=0)
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
             else
                startActivity(new Intent(SplashActivity.this,CreateActivity.class));
            finish();
        },3000);
    }
}