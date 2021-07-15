package com.narendra.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Model.PeriodTimeModel;
import com.narendra.timetable.R;

import java.util.ArrayList;

public class PeriodAdapter extends RecyclerView.Adapter<PeriodAdapter.ViewHolder> {
    private ArrayList<PeriodTimeModel> localDataSet;
    private Context localContext;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_period_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position==0) {
            holder.getPeriod().setText("PERIOD");
            holder.getFrom().setText("FROM");
            holder.getTo().setText("TO");
        }
        else{
            holder.getPeriod().setText("PERIOD "+position);
            holder.getFrom().setText(localDataSet.get(position).getFrom().toString());
            holder.getTo().setText(localDataSet.get(position).getTo().toString());
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView period;
        private final TextView from;
        private final TextView to;

        public ViewHolder(View view){
            super(view);
            period=view.findViewById(R.id.period);
            from=view.findViewById(R.id.from);
            to=view.findViewById(R.id.to);
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
    }
    public PeriodAdapter(Context context,ArrayList<PeriodTimeModel> dataSet){
        localDataSet=dataSet;
        localContext=context;
    }
}
