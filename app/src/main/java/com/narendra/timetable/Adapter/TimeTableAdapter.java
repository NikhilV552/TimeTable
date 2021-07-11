package com.narendra.timetable.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.narendra.timetable.Model.Content;
import com.narendra.timetable.R;

import java.util.ArrayList;

public class TimeTableAdapter extends ArrayAdapter<Content> {
    public TimeTableAdapter(@NonNull Context context, ArrayList<Content> contentArrayList){
        super(context,0,contentArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        View listitemView=convertView;
        if(listitemView==null){
            listitemView= LayoutInflater.from(getContext()).inflate(R.layout.grid_fragment_item,parent,false);
        }
        Content content=getItem(position);
        TextView value=listitemView.findViewById(R.id.value);
        value.setText(content.getValue());
        return listitemView;
    }
}
