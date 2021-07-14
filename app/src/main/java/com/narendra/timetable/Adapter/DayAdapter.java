package com.narendra.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ViewHolder> {
    private HashMap<String,ArrayList<RowModel>> localDataSet;
    private String[] localDays;
    private Context localContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_day_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
     holder.getDay().setText(localDays[position]);
     LinearLayoutManager layoutManager=new LinearLayoutManager(localContext);

    }

    @Override
    public int getItemCount() {
        return localDays.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView day;
     //   private final RecyclerView column;

        public ViewHolder(View view){
            super(view);
            day=view.findViewById(R.id.day);
       //     column=view.findViewById(R.id.recyclerColumn);
        }

        public TextView getDay(){ return day;}
//        public RecyclerView getColumn(){return 0;}
    }
    public DayAdapter(Context context,String days[], HashMap<String,ArrayList<RowModel>> dataSet){
        localDataSet=dataSet;
        localDays=days;
//        localContext=context;
    }
}
