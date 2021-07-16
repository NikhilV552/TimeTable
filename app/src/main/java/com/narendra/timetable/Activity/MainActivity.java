package com.narendra.timetable.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Timetable");
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar,R.string.navigation_open_drawer,R.string.navigation_close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.item1:
                    Toast.makeText(MainActivity.this, "Clicked on item1", Toast.LENGTH_LONG).show();break;
                case R.id.item2:
                    Toast.makeText(MainActivity.this, "Clicked on item2", Toast.LENGTH_LONG).show();break;
                case R.id.item3:
                    Toast.makeText(MainActivity.this, "Clicked on item3", Toast.LENGTH_LONG).show();break;
                case R.id.item4:
                    Toast.makeText(MainActivity.this, "Clicked on item4", Toast.LENGTH_LONG).show();break;
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });


        DatabaseTimeTableHelper timeTableHelper = new DatabaseTimeTableHelper(MainActivity.this);
        TimeTableModel model1 = GenerateModelData.generateData("TIMETABLE_2", 2, 8);
        timeTableHelper.createTable(model1);
        System.out.println(model1);
        int temp = timeTableHelper.getTimeTableId2("TIMETABLE_2");
        Toast.makeText(MainActivity.this, "timetableid=" + temp, Toast.LENGTH_LONG).show();
        PeriodTimeModel initial = new PeriodTimeModel(new Time(00000000), new Time(00000000));
        ArrayList<PeriodTimeModel> period = model1.getPeriodTimes();
        HashMap<String, ArrayList<RowModel>> timeTableValues = model1.getTimeTableValues();
        period.add(0, initial);

//
//        GridLayoutManager periodLayoutManager=new GridLayoutManager(this,period.size());
//        PeriodAdapter periodAdapter=new PeriodAdapter(this,period);
//        recyclerPeriod.setLayoutManager(periodLayoutManager);
//        recyclerPeriod.setAdapter(periodAdapter);

//
//        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(this);
//        DayAdapter dayAdapter=new DayAdapter(this,model1.getDays(),timeTableValues,model1.getRowNames());
//        recyclerDay.setLayoutManager(dayLayoutManager);
//        recyclerDay.setAdapter(dayAdapter);


        ArrayList<String> timeTable = new ArrayList<String>();
        timeTable.add("TimeTable_1");
        timeTable.add("TimeTable_2");
        timeTable.add("TimeTable_3");
        timeTable.add("TimeTable_4");
        timeTable.add("TimeTable_5");
        timeTable.add("TimeTable_6");
        timeTable.add("TimeTable_7");
        timeTable.add("TimeTable_8");
        timeTable.add("TimeTable_9");
        timeTable.add("TimeTable_10");
        timeTable.add("TimeTable_11");

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.home)
            drawerLayout.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}