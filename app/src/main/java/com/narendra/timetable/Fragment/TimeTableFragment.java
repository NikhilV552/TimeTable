package com.narendra.timetable.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Adapter.DayAdapter;
import com.narendra.timetable.Adapter.PeriodAdapter;
import com.narendra.timetable.Database.DatabaseTimeTableHelper;
import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;
import com.narendra.timetable.exampleDemo.GenerateModelData;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableFragment extends Fragment {

    RecyclerView recyclerPeriod;
    RecyclerView recyclerDay;
    private  boolean isEdit;
    TimeTableModel model1;
    Context ctx;
    public TimeTableFragment() {
        // Required empty public constructor
    }
    public TimeTableFragment(boolean isEdit, Context ctx) {
        // Required empty public constructor
        this.isEdit=isEdit;
        this.ctx=ctx;
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
        //getArguments();
        String timeTablename="TIMETABLE_4";
        DatabaseTimeTableHelper timeTableHelper = new DatabaseTimeTableHelper(getActivity());
        model1=timeTableHelper.getTheModelFortheLoadingOfData(timeTablename);
        timeTableHelper.loadTheDataForATimeTable(timeTablename,model1);
        ArrayList<PeriodTimeModel> period=model1.getPeriodTimes();
        HashMap<String,ArrayList<RowModel>> timeTableValues=model1.getTimeTableValues();
        GridLayoutManager periodLayoutManager=new GridLayoutManager(ctx,period.size()+2);
        PeriodAdapter periodAdapter=new PeriodAdapter(ctx,period,false);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);
        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(ctx);
        DayAdapter dayAdapter=new DayAdapter(ctx,model1.getDays(),timeTableValues,model1.getRowNames(),false);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);
        /*TimeTableModel model1 = GenerateModelData.generateData("TIMETABLE_2", 2, 8);
        timeTableHelper.createTable(model1);
        System.out.println(model1);
        int temp = timeTableHelper.getTimeTableId2("TIMETABLE_2");
        Toast.makeText(getContext(), "timetableid=" + temp, Toast.LENGTH_LONG).show();
        PeriodTimeModel initial = new PeriodTimeModel(new Time(00000000), new Time(00000000));
        ArrayList<PeriodTimeModel> period = model1.getPeriodTimes();
        HashMap<String, ArrayList<RowModel>> timeTableValues = model1.getTimeTableValues();
        period.add(0, initial);


        GridLayoutManager periodLayoutManager=new GridLayoutManager(getContext(),period.size());
        PeriodAdapter periodAdapter=new PeriodAdapter(getContext(),period,true);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);


        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(getContext());
        DayAdapter dayAdapter=new DayAdapter(getContext(),model1.getDays(),timeTableValues,model1.getRowNames(),isEdit);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);*/


        return view;

    }
}