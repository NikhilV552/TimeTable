package com.narendra.timetable.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.ArrayList;
import java.util.HashMap;

public class TimeTableFragment extends Fragment {

    RecyclerView recyclerPeriod;
    RecyclerView recyclerDay;
    private boolean isEdit;
    TimeTableModel model1;

    public TimeTableFragment() {
        // Required empty public constructor
    }
    public TimeTableFragment(boolean isEdit) {
        // Required empty public constructor
        this.isEdit=isEdit;
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


        /*DatabaseTimeTableHelper timeTableHelper = new DatabaseTimeTableHelper(getActivity());
        String tableName=getArguments().getString("tableName");
        TimeTableModel model1 = timeTableHelper.getTheModelFortheLoadingOfData(tableName);
        ArrayList<PeriodTimeModel> period = model1.getPeriodTimes();

        HashMap<String, ArrayList<RowModel>> timeTableValues = model1.getTimeTableValues();


        GridLayoutManager periodLayoutManager=new GridLayoutManager(getContext(),period.size());
        PeriodAdapter periodAdapter=new PeriodAdapter(getContext(),period,true);
        recyclerPeriod.setLayoutManager(periodLayoutManager);
        recyclerPeriod.setAdapter(periodAdapter);


        LinearLayoutManager dayLayoutManager=new LinearLayoutManager(getContext());
        DayAdapter dayAdapter=new DayAdapter(getContext(),model1.getDays(),timeTableValues,model1.getRowNames(),isEdit);
        recyclerDay.setLayoutManager(dayLayoutManager);
        recyclerDay.setAdapter(dayAdapter);*/

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

        return view;

    }
}