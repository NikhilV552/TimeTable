package com.narendra.timetable.Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.narendra.timetable.R;

import java.util.ArrayList;

public class RowNamesEditAdapter extends RecyclerView.Adapter<RowNamesEditAdapter.ViewHolder> {

    ArrayList<String> rowNames;
    Context context;

    public RowNamesEditAdapter(Context context, ArrayList<String> rowNames){
        this.rowNames=rowNames;
        this.context=context;
    }

    @NonNull
    @Override
    public RowNamesEditAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_values_edit,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowNamesEditAdapter.ViewHolder holder, int position) {
        holder.getRowText().setText("ROW"+(position+1)+" : ");
        holder.getRowName().setText(rowNames.get(position));
        holder.getRowName().setHint("Enter the row name of row "+(position+1));
        holder.getRowName().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                rowNames.set(position,s.toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return rowNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView rowText;
        private final EditText rowName;

        TextView getRowText(){
            return rowText;
        }
        EditText getRowName(){
            return rowName;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowText=itemView.findViewById(R.id.rowTextView);
            rowName=itemView.findViewById(R.id.rowNameEditTextView);
        }
    }
}
