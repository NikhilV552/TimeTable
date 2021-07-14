package com.narendra.timetable.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Model.TimeTableModel;
import com.narendra.timetable.R;

import java.util.ArrayList;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private ArrayList<TimeTableModel> localDataSet;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_day_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.getDay().setText(localDataSet.get(position).getDays()[position]);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView day;
        private final RecyclerView column;

        public ViewHolder(View view){
            super(view);
            day=view.findViewById(R.id.day);
            column=view.findViewById(R.id.recyclerColumn);
        }

        public TextView getDay(){ return day;}
        public RecyclerView getColumn(){return column;}
    }
    public DayAdapter(ArrayList<TimeTableModel> dataSet){ localDataSet=dataSet;}
}
