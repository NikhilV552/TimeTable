package com.narendra.timetable.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
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
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0504AA")));
        }
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar,R.string.navigation_open_drawer,R.string.navigation_close_drawer);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        Menu menu=navigationView.getMenu();
        DatabaseTimeTableHelper timeTableHelper=new DatabaseTimeTableHelper(this);
        ArrayList<String> timeTableNames=timeTableHelper.getAllTimeTableNamesPresent();
        timeTable=timeTableNames;
        for(String item: timeTable)
            menu.add(R.id.group,Menu.FIRST,Menu.FIRST+timeTable.indexOf(item),item);
        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case Menu.FIRST:
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
////            progressLayout.setVisibility(View.GONE);
////            progressBar.setVisibility(View.GONE);
//            TimeTableFragment timeTableFragment=new TimeTableFragment();
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout,timeTableFragment);
//            fragmentTransaction.commit();
//            getSupportActionBar().setTitle("item1");

        recyclerPeriod=findViewById(R.id.recyclerPeriod);
        recyclerDay=findViewById(R.id.recyclerDay);

        //DatabaseTimeTableHelper timeTableHelper=new DatabaseTimeTableHelper(this);
//
        //model1= GenerateModelData.generateData("TIMETABLE_4",4,9);
        model1=timeTableHelper.getTheModelFortheLoadingOfData("TIMETABLE_4");
        timeTableHelper.createTable(model1);
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
        PeriodAdapter periodAdapter=new PeriodAdapter(this,period,false);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
//        for(String i: model1.getDays()){
//            System.out.println(i);
//        }
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(this);
        DayAdapter dayAdapter=new DayAdapter(this,model1.getDays(),timeTableValues,model1.getRowNames(),false);
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
        /*DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(this);
        try {
            helper.updateTheTimeTable(model1);
            Toast.makeText(this, "TimeTable "+ model1.getTimeTableName()+" updated successfully", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }*/
        Intent i=new Intent(this,EditTimeTableActivity.class);
        Bundle bundle=new Bundle();
        bundle.putInt("TIMETABLEID",model1.getTimeTableId());
        bundle.putBoolean("isNew",false);
        i.putExtras(bundle);
        startActivity(i);
    }
}