package com.narendra.timetable.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.narendra.timetable.R;
import com.narendra.timetable.util.CreateTableParamaters;

public class CreateActivity extends AppCompatActivity {
Toolbar toolbar;
EditText tableName;
EditText noPeriod;
EditText noRow;

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
        String timeTableName=tableName.getText().toString();
        String period=noPeriod.getText().toString();
        String row=noRow.getText().toString();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setPositiveButton("Ok", null);
        if(timeTableName.isEmpty()){
            builder.setTitle("Table Name cannot be empty !");
            builder.create();
            builder.show();
        }
        else if(period.isEmpty()){
            builder.setTitle("Number of Periods cannot be empty !");
            builder.create();
            builder.show();
        }
        else if(row.isEmpty()) {
            builder.setTitle("Number of Rows cannot be empty !");
            builder.create();
            builder.show();
        }
        else {
            try{
                Integer.parseInt(period);
            }catch(Exception e){
                builder.setTitle("Number of periods should be integer !");
                builder.create();
                builder.show();
                return ;
            }
            try{
                Integer.parseInt(row);
            }catch (Exception e){
                builder.setTitle("Number of rows should be integer !");
                builder.create();
                builder.show();
                return ;
            }
            Intent i = new Intent(this, EditTimeTableActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString(CreateTableParamaters.TimeTableName.toString(), timeTableName);
            bundle.putInt(CreateTableParamaters.NumberOfPeriods.toString(), Integer.parseInt(period));
            bundle.putInt(CreateTableParamaters.NumberOfRows.toString(), Integer.parseInt(row));
            bundle.putBoolean(CreateTableParamaters.isNew.toString(), true);
            bundle.putInt(CreateTableParamaters.TimeTableId.toString(),-1);
            i.putExtras(bundle);
            startActivity(i);
            finish();
        }
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