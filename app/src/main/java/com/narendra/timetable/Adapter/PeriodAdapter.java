package com.narendra.timetable.Adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.R;

import java.sql.Time;
import java.util.ArrayList;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.ViewHolder> {
    private ArrayList<PeriodTimeModel> localDataSet;
    private Context localContext;
    private boolean isEdit;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(!isEdit) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_period_single_row, parent, false);
            return new ViewHolder(view,isEdit);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_period_single_row_edit, parent, false);
            return new ViewHolder(view,isEdit);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position==0 ){
            if(holder.getIsEdit()) {
                holder.getPeriod().setText("PERIOD");
                holder.getFromEdit().setText("FROM");
                holder.getToEdit().setText("TO");
                holder.getFromEdit().setEnabled(false);
                holder.getToEdit().setEnabled(false);
            }else {
                holder.getPeriod().setText("PERIODs");
                holder.getFrom().setText("---");
                holder.getTo().setText("DAYS");
            }
        }
        else if(position==1  ) {
            Log.v("PERIOD 0","IN PERIOD =1 RENDERING");
            if(!isEdit) {
                holder.getPeriod().setText("PERIOD");
                holder.getFrom().setText("FROM");
                holder.getTo().setText("TO");
            }else{
                holder.getFromEdit().setText(localDataSet.get(0).getFrom().toString());
                holder.getToEdit().setText(localDataSet.get(0).getTo().toString());
                holder.getPeriod().setText("PERIOD123");
                holder.getToEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Time t=localDataSet.get(0).getTo();
                        TimePickerDialog timePicker=new TimePickerDialog(localContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Time t1=new Time(hourOfDay,minute,0);
                                localDataSet.get(0).setTo(t1);
                                holder.getToEdit().setText(t1.toString());
                                Log.v("PERIOD 1 CHANGED TO",localDataSet.get(0).getTo().toString());
                            }
                        },0,0,true);
                        Log.v("TIME PICKER","SHOWN");
                        timePicker.show();
                    }
                });
                holder.getFromEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Time t=localDataSet.get(0).getFrom();
                        TimePickerDialog timePicker=new TimePickerDialog(localContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Time t1=new Time(hourOfDay,minute,0);
                                localDataSet.get(0).setFrom(t1);
                                holder.getFromEdit().setText(t1.toString());
                                Log.v("PERIOD 1 CHANGED TO",localDataSet.get(0).getFrom().toString());
                            }
                        },0,0,true);
                        Log.v("TIME PICKER","SHOWN");
                        timePicker.show();
                    }
                });
                /*holder.getFromEdit().addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String[] values=s.toString().split(":");
                        if(values.length==3) {
                            try {
                                int seconds = Integer.parseInt(values[2]);
                                int minutes = Integer.parseInt(values[1]);
                                int hours = Integer.parseInt(values[0]);
                                localDataSet.get(0).setFrom(new Time(hours, minutes, seconds));
                                Log.v("EDIT CHANGED", "period " + (0) + "changed to time to " + values);
                                String res = "EDIT CHANGED" + " period " + (0) + "changed to time to " + localDataSet.get(position - 2).getFrom().toString();
                                //Toast.makeText(localContext, res, Toast.LENGTH_LONG).show();
                            }catch (Exception e){
                                /*try {
                                    holder.getFromEdit().setText(localDataSet.get(0).getFrom().toString());
                                }catch(Exception e1){
                                    e1.printStackTrace();
                                }
                            }
                        }else {
                            holder.getFromEdit().setText(localDataSet.get(0).getFrom().toString());
                        }
                    }
                });
                Log.v("PERIOD 0","Period zero finished");*/

            }
        }
        else{
            if(!holder.getIsEdit()) {
                holder.getFrom().setText(localDataSet.get(position-2).getFrom().toString());
                holder.getTo().setText(localDataSet.get(position-2).getTo().toString());
                holder.getPeriod().setText("PERIOD "+(position-1));
            }else {
                holder.getPeriod().setText("PERIOD "+(position));
                holder.getFromEdit().setText(localDataSet.get(position - 1).getFrom().toString());
                holder.getToEdit().setText(localDataSet.get(position - 1).getTo().toString());
                holder.getToEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Time t=localDataSet.get(position - 1).getTo();
                        TimePickerDialog timePicker=new TimePickerDialog(localContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Time t1=new Time(hourOfDay,minute,0);
                                localDataSet.get(position - 1).setTo(t1);
                                holder.getToEdit().setText(t1.toString());
                                Log.v("PERIOD 1 CHANGED TO",localDataSet.get(position - 1).getTo().toString());
                            }
                        },0,0,true);
                        Log.v("TIME PICKER","SHOWN");
                        timePicker.show();
                    }
                });
                holder.getFromEdit().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Time t=localDataSet.get(position - 1).getFrom();
                        TimePickerDialog timePicker=new TimePickerDialog(localContext, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                Time t1=new Time(hourOfDay,minute,0);
                                localDataSet.get(position - 1).setFrom(t1);
                                holder.getFromEdit().setText(t1.toString());
                                Log.v("PERIOD 1 CHANGED TO",localDataSet.get(position - 1).getFrom().toString());
                            }
                        },t.getHours(),t.getMinutes(),true);
                        Log.v("TIME PICKER","SHOWN");
                        timePicker.show();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {

        if(isEdit) {
            return localDataSet.size()+1;
        }
        else {
            return localDataSet.size()+2;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView period;
        private final TextView from;
        private final TextView to;
        private final EditText fromTime;
        private final EditText toTime;
        private boolean isEdit;

        public ViewHolder(View view,boolean isEdit){
            super(view);
            this.isEdit=isEdit;
            if(!isEdit) {
                period=view.findViewById(R.id.period);
                from = view.findViewById(R.id.from);
                to = view.findViewById(R.id.to);
                fromTime = null;
                toTime = null;

            }else {
                from=null;
                to=null;
                period=view.findViewById(R.id.periodEdit);
                fromTime = view.findViewById(R.id.fromEdit);
                toTime = view.findViewById(R.id.toEdit);
            }
        }
        public TextView getPeriod(){
            return period;
        }
        public TextView getFrom(){
            return from;
        }
        public TextView getTo(){
            return to;
        }
        public EditText getFromEdit(){
            return  fromTime;
        }
        public EditText getToEdit(){
            return toTime;
        }
        public boolean getIsEdit(){
            return this.isEdit;
        }
    }
    public PeriodAdapter(Context context,ArrayList<PeriodTimeModel> dataSet,boolean isEdit){
        localDataSet=dataSet;
        localContext=context;
        this.isEdit=isEdit;
    }
}
