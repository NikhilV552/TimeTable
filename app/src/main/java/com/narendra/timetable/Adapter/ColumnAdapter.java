package com.narendra.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.Model.RowModel;
import com.narendra.timetable.R;

import java.util.ArrayList;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ViewHolder> {
    private ArrayList<RowModel> localDataSet;
    private Context localContext;
    private ArrayList<String> rowNames;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_column_single_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LinearLayoutManager layoutManager=new LinearLayoutManager(localContext);
        RowAdapter rowAdapter=null;
        if(position==0){
            rowAdapter = new RowAdapter(localContext, rowNames);
        }else {

            rowAdapter = new RowAdapter(localContext, localDataSet.get(position-1).getRowValues());
        }
        holder.getRecyclerRow().setLayoutManager(layoutManager);
        holder.getRecyclerRow().setAdapter(rowAdapter);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size()+1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final RecyclerView recyclerRow;

        public ViewHolder(View view){
            super(view);
            recyclerRow=view.findViewById(R.id.recyclerRow);
        }
        public RecyclerView getRecyclerRow(){
            return recyclerRow;
        }
    }
    public ColumnAdapter(Context context,ArrayList<RowModel> dataSet,ArrayList<String> rowNames){
        this.rowNames=rowNames;
        localContext=context;
        localDataSet=dataSet;
    }
}
