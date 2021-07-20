package com.narendra.timetable.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.narendra.timetable.R;

public class CreateActivity extends AppCompatActivity {
Toolbar toolbar;
TextInputEditText tableName;
TextInputEditText noPeriod;
TextInputEditText noRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        toolbar = findViewById(R.id.toolbar);
        tableName=findViewById(R.id.timeTableName);
        noPeriod=findViewById(R.id.noPeriod);
        noRow=findViewById(R.id.noRow);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Create Timetable");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0504AA")));
        }
    }
    public void create(View view){
        System.out.println(tableName.getEditableText().toString());
        System.out.println(noPeriod.getEditableText().toString());
        System.out.println(noRow.getEditableText().toString());
    }
    public void back(View view){
        onBackPressed();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}