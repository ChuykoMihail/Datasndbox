package com.example.datasndbox10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.ArrayList;
public class EditColumnActivity extends AppCompatActivity {




    Button saveButton;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter adapter;
    EditText tableName;

    ArrayList<TableField> fields = new ArrayList<TableField>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_column);
        saveButton = findViewById(R.id.saveTable);

        tableName = findViewById(R.id.editTextTextPersonName);


        setInitialData();
        recyclerView = (RecyclerView) findViewById(R.id.list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        // создаем адаптер
        adapter = new RecyclerAdapter(this, fields);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = RecyclerAdapter.toSQLEditColumns.generateSQL(adapter.fields);
                MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName.getText().toString()+" ("+command+")");
            }
        });
    }

    private void setInitialData(){

        fields.add(new TableField ("", "Integer",false, false, false));
    }

    public void addNewField(View view) {
        fields.add(new TableField ("", "Integer",false, false, false));
        adapter.notifyItemChanged(adapter.getItemCount()-1);
    }

}
