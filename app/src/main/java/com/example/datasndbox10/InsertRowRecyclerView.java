package com.example.datasndbox10;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InsertRowRecyclerView extends RecyclerView.Adapter<InsertRowRecyclerView.mViewHolder> {

    LayoutInflater inflater;
    ArrayList<JustInserRowTextClass> fields;


    InsertRowRecyclerView(Context context, ArrayList<JustInserRowTextClass> fields){
        this.fields =fields;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public InsertRowRecyclerView.mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.insert_row_element,parent,false);
        return new mViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InsertRowRecyclerView.mViewHolder holder, int position) {
        JustInserRowTextClass string = fields.get(position);
        holder.editTextForData.setHint(fields.get(position).getDataForInsert());
        holder.editTextForData.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                string.setDataForInsert(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public static class mViewHolder extends RecyclerView.ViewHolder {
        EditText editTextForData;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextForData = itemView.findViewById(R.id.insert_tow_field_EditText);
        }
    }
}
