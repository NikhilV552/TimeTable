package com.narendra.timetable.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

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
        if(position==0){
            if(!holder.getIsEdit()) {
                holder.getPeriod().setText("PERIODS");
                holder.getFrom().setText("----");
                holder.getTo().setText("DAYS");
            }else {
                holder.getPeriod().setText("PERIODS");
                holder.getFromEdit().setText("----");
                holder.getToEdit().setText("DAYS");
                holder.getFromEdit().setEnabled(false);
                holder.getToEdit().setEnabled(false);
            }
        }
        else if(position==1) {
            if(holder.isEdit) {
                holder.getPeriod().setText("PERIOD");
                holder.getFromEdit().setText("FROM");
                holder.getToEdit().setText("TO");
                holder.getFromEdit().setEnabled(false);
                holder.getToEdit().setEnabled(false);
            }else {
                holder.getPeriod().setText("PERIOD");
                holder.getFrom().setText("FROM");
                holder.getTo().setText("TO");
            }
        }
        else{
            holder.getPeriod().setText("PERIOD "+(position-2));
            if(!holder.getIsEdit()) {
                holder.getFrom().setText(localDataSet.get(position).getFrom().toString());
                holder.getTo().setText(localDataSet.get(position).getTo().toString());
            }else {
                /*if(position==localDataSet.size()+1){
                    holder.getFromEdit().setText(localDataSet.get(localDataSet.size()-2).getFrom().toString());
                    holder.getToEdit().setText(localDataSet.get(localDataSet.size()-2).getTo().toString());
                }else {*/
                    holder.getFromEdit().setText(localDataSet.get(position - 2).getFrom().toString());
                    holder.getToEdit().setText(localDataSet.get(position - 2).getTo().toString());
                    holder.getToEdit().addTextChangedListener(new TextWatcher() {

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
                                    localDataSet.get(position - 2).setTo(new Time(hours, minutes, seconds));
                                    Log.v("EDIT CHANGED", "period " + (position - 2) + "changed to time to " + values);
                                    String res = "EDIT CHANGED" + " period " + (position - 2) + "changed to time to " + localDataSet.get(position - 2).getTo().toString();
                                    //Toast.makeText(localContext, res, Toast.LENGTH_LONG).show();
                                }catch(Exception e){
                                    holder.getToEdit().setText(localDataSet.get(position-1).getTo().toString());
                                }
                            }else {
                                holder.getToEdit().setText(localDataSet.get(position-1).getTo().toString());
                            }
                        }
                    });
                    holder.getFromEdit().addTextChangedListener(new TextWatcher() {

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
                                    localDataSet.get(position - 2).setFrom(new Time(hours, minutes, seconds));
                                    Log.v("EDIT CHANGED", "period " + (position - 2) + "changed to time to " + values);
                                    String res = "EDIT CHANGED" + " period " + (position - 2) + "changed to time to " + localDataSet.get(position - 2).getFrom().toString();
                                    //Toast.makeText(localContext, res, Toast.LENGTH_LONG).show();
                                }catch (Exception e){
                                    holder.getFromEdit().setText(localDataSet.get(position-2).getFrom().toString());
                                }
                            }else {
                                holder.getFromEdit().setText(localDataSet.get(position-2).getFrom().toString());
                            }
                        }
                    });
                    Log.v("IN PERIOD ADAPTER", (position-2) + " finished");
                //}
            }
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size()+2;
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
