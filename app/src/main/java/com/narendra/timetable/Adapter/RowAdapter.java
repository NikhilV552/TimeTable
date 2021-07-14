package com.narendra.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.R;

import java.util.ArrayList;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;
    private Context localContext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        System.out.println(localDataSet.get(position));
        holder.getValue().setText(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textValue;

        public ViewHolder(View view) {
            super(view);
            textValue=view.findViewById(R.id.value);

        }
        public TextView getValue(){ return textValue;}
    }
    public RowAdapter(Context context,ArrayList<String> dataSet){
        localContext=context;
        localDataSet=dataSet;
//        System.out.println(dataSet);
    }
}
