package com.example.datasndbox10;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import com.example.datasndbox10.ForeignKeyActivity.ForeignKeyActivity;

import java.util.List;

import static androidx.core.app.ActivityCompat.startActivityForResult;
import static java.security.AccessController.getContext;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    public static String FKname="";

    private final LayoutInflater inflater;
    protected final List<TableField> fields;
    public Context context;

    RecyclerAdapter(Context context, List<TableField> _fields) {
        this.fields = _fields;
        this.inflater = LayoutInflater.from(context);
        this.context=context;
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.activity_elements, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        TableField state = fields.get(position);

        holder.type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state.setType((String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        holder.flagView.setText(state.getFlagResource());
        holder.flagView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                state.setFlagResource(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        holder.checkBox1.setChecked(state.getCheckBox1Resource());
        holder.checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    state.setCheckBox1Resource(true);
                } else {
                    state.setCheckBox1Resource(false);
                }
            }
        });

        holder.checkBox2.setChecked(state.getCheckBox2Resource());
        holder.checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    state.setCheckBox2Resource(true);
                } else {
                    state.setCheckBox2Resource(false);
                }
            }
        });

        holder.checkBox3.setChecked(state.getCheckBox3Resource());
        holder.checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    state.setCheckBox3Resource(true);
                    FKname = state.getFlagResource();

                } else {
                    state.setCheckBox3Resource(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final EditText flagView;
        Spinner type_spinner;
        final CheckBox checkBox1, checkBox2, checkBox3;
        ViewHolder(View view, Context context){
            super(view);
            flagView = (EditText)view.findViewById(R.id.editTextTextPersonName2);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.types_of_data, android.R.layout.simple_spinner_item);
            type_spinner = (Spinner) view.findViewById(R.id.spinner3);
            type_spinner.setAdapter(adapter);
            checkBox1 = (CheckBox) view.findViewById(R.id.checkBox1);
            checkBox2 = (CheckBox) view.findViewById(R.id.checkBox2);
            checkBox3 = (CheckBox) view.findViewById(R.id.checkBox3);
        }
    }

    public static class toSQLEditColumns{
        public static int columnsNum;
        private Context context;

        public static String generateField(TableField myField){
            String SQLfield="";
            if (myField.getFlagResource()!=""){
                SQLfield+=myField.getFlagResource()+" ";
                if (myField.getType().equals("Integer")) SQLfield+="INTEGER";
                else SQLfield+="TEXT";
                if (myField.getCheckBox1Resource()) SQLfield+=" NOT NULL";
                if (myField.getCheckBox2Resource()) SQLfield+= " PRIMARY KEY";
                if (myField.getCheckBox3Resource()) {
                    FKname = myField.getFlagResource();
                }
            }
            return SQLfield;
        }

        public static String generateSQL(List<TableField> _fields, Context context){
            columnsNum = _fields.size();
            String finalSQL="";
            for (int i=0; i<columnsNum-1;i++)   {
                finalSQL+=generateField(_fields.get(i))+", ";
            }
            finalSQL+=generateField(_fields.get(columnsNum-1));
            return finalSQL;
        }
    }
}