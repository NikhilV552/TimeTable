package com.narendra.timetable.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Activity.EditTimeTableActivity;
import com.narendra.timetable.Activity.SplashActivity;
import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.util.CreateTableParamaters;

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableFragment extends Fragment {

    RecyclerView recyclerPeriod;
    RecyclerView recyclerDay;
    private boolean isEdit;
    TimeTableModel model1;
    Button updateButton;
    Button deleteButton;

    public int getTimeTableId(){
        return this.model1.getTimeTableId();
    }

    public TimeTableFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_time_table, container, false);
        recyclerPeriod=view.findViewById(R.id.recyclerPeriod);
        recyclerDay=view.findViewById(R.id.recyclerDay);
        updateButton=view.findViewById(R.id.updateTimeTable);
        deleteButton=view.findViewById(R.id.deleteBtn);


        String timeTablename=getArguments().getString("tableName");
        DatabaseTimeTableHelper timeTableHelper = new DatabaseTimeTableHelper(getActivity());
        model1=timeTableHelper.getTheModelFortheLoadingOfData(timeTablename);
        timeTableHelper.loadTheDataForATimeTable(timeTablename,model1);
        ArrayList<PeriodTimeModel> period=model1.getPeriodTimes();
        HashMap<String,ArrayList<RowModel>> timeTableValues=model1.getTimeTableValues();
        GridLayoutManager periodLayoutManager=new GridLayoutManager(getContext(),period.size()+2);
        PeriodAdapter periodAdapter=new PeriodAdapter(getContext(),period,false);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(getContext());
        DayAdapter dayAdapter=new DayAdapter(getContext(),model1.getDays(),timeTableValues,model1.getRowNames(),false);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);
        System.out.println(model1);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Going to edit"+":"+model1.getTimeTableName(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getContext(), EditTimeTableActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt(CreateTableParamaters.TimeTableId.toString(),model1.getTimeTableId());
                bundle.putBoolean(CreateTableParamaters.isNew.toString(),false);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Going to delete"+":"+model1.getTimeTableName(), Toast.LENGTH_SHORT).show();
                DatabaseTimeTableHelper helper=new DatabaseTimeTableHelper(getContext());
                try{
                    helper.deleteTimeTable(model1.getTimeTableId());
                    Intent i=new Intent(getContext(), SplashActivity.class);
                    Toast.makeText(getContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }catch (Exception e){
                    Toast.makeText(getContext(), "Sorru could not delete due to :"+e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }
}