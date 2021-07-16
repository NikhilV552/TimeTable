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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.narendra.timetable.Fragment.TimeTableFragment;
import com.narendra.timetable.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
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