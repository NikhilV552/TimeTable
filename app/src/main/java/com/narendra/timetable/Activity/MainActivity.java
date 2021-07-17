package com.narendra.timetable.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Fragment.TimeTableFragment;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    RecyclerView recyclerPeriod,recyclerDay;
    TimeTableModel model1;
//    RelativeLayout progressLayout;
//    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        toolbar = findViewById(R.id.toolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
//        progressBar=findViewById(R.id.progressBar);
//        progressLayout=findViewById(R.id.progressLayout);


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
//                    progressLayout.setVisibility(View.VISIBLE);
//                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Clicked on item1", Toast.LENGTH_LONG).show();break;
                case R.id.item2:
                    Toast.makeText(MainActivity.this, "Clicked on item2", Toast.LENGTH_LONG).show();break;
                case R.id.item3:
                    Toast.makeText(MainActivity.this, "Clicked on item3", Toast.LENGTH_LONG).show();break;
                case R.id.item4:
                    Toast.makeText(MainActivity.this, "Clicked on item4", Toast.LENGTH_LONG).show();break;
            }
//            progressLayout.setVisibility(View.GONE);
//            progressBar.setVisibility(View.GONE);
            drawerLayout.closeDrawer(GravityCompat.START);
            TimeTableFragment timeTableFragment=new TimeTableFragment();
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout,timeTableFragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle("item1");
            return true;
        });

        recyclerPeriod=findViewById(R.id.recyclerPeriod);
        recyclerDay=findViewById(R.id.recyclerDay);

        DatabaseTimeTableHelper timeTableHelper=new DatabaseTimeTableHelper(this);
//
        //model1= GenerateModelData.generateData("TIMETABLE_4",4,9);
        model1=timeTableHelper.getTheModelFortheLoadingOfData("TIMETABLE_4");
        //timeTableHelper.createTable(model1);
        System.out.println(model1);
        int temp=timeTableHelper.getTimeTableId2("TIMETABLE_4");
        Toast.makeText(this, "timetableid="+temp, Toast.LENGTH_LONG).show();
        ArrayList<PeriodTimeModel> period=model1.getPeriodTimes();
        //DatabaseTimeTableHelper db=new DatabaseTimeTableHelper(this);
        timeTableHelper.loadTheDataForATimeTable("TIMETABLE_4",model1);
        HashMap<String,ArrayList<RowModel>> timeTableValues=model1.getTimeTableValues();
        ///PeriodTimeModel initial=new PeriodTimeModel(period.get(0).getFrom(),period.get(0).getTo());
        //PeriodTimeModel first=new PeriodTimeModel(period.get(1).getFrom(),period.get(1).getTo());
        //period.add(0,initial);
//        System.out.println(period.size());
        //period.add(0,first);
        //period.add(0,initial);
        System.out.println("Period ArrayList :");
        for(PeriodTimeModel i: period)
            System.out.println(i);
        System.out.println("The End");
        GridLayoutManager periodLayoutManager=new GridLayoutManager(this,period.size()+2);
        PeriodAdapter periodAdapter=new PeriodAdapter(this,period,true);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
//        for(String i: model1.getDays()){
//            System.out.println(i);
//        }
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(this);
        DayAdapter dayAdapter=new DayAdapter(this,model1.getDays(),timeTableValues,model1.getRowNames(),true);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);


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

    public void showTimeTablePeriodValues(View v){
        Log.d("UPDATED VALUE",model1.toString());
    }

    public void updateTheTimeTable(View v){
        DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        try {
            helper.updateTheTimeTable(model1);
            Toast.makeText(this, "TimeTable "+ model1.getTimeTableName()+" updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}