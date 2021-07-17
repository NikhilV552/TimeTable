package com.narendra.timetable.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.R;

import java.util.ArrayList;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolder> {
    private ArrayList<String> localDataSet;
    private Context localContext;
    private boolean isEdit;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(!isEdit) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_single_row, parent, false);
            return new ViewHolder(view,isEdit);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_single_row_edit, parent, false);
            return new ViewHolder(view,isEdit);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(holder.isEdit){
            holder.getTheEditValue().setText(localDataSet.get(position));
            holder.getTheEditValue().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    localDataSet.set(position,s.toString());
                }
            });
        }else {
            holder.getValue().setText(localDataSet.get(position));
            holder.getValue().setSelected(true);
        }
        //holder.getValue().setMovementMethod(new ScrollingMovementMethod());
        //holder.getValue().setHorizontallyScrolling(true);
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textValue;
        private final EditText editTextView;
        private boolean isEdit;

        public ViewHolder(View view,boolean isEdit) {
            super(view);
            if(!isEdit) {
                textValue = view.findViewById(R.id.value);
                editTextView=null;
            }else {
                editTextView=view.findViewById(R.id.valueEdit);
                textValue=null;
            }
            this.isEdit=isEdit;

        }
        public TextView getValue(){ return textValue;}
        public EditText getTheEditValue(){return editTextView;}
    }
    public RowAdapter(Context context,ArrayList<String> dataSet,boolean isEdit){
        this.isEdit=isEdit;
        localContext=context;
        localDataSet=dataSet;
    }
}
