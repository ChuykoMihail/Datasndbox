package com.example.datasndbox10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InsertRowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button SaveButton;
    Button CancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);



        recyclerView =findViewById(R.id.insert_new_row_recyclerView);
        SaveButton = findViewById(R.id.insert_new_row_button);
        CancelButton = findViewById(R.id.insert_new_row_cancel_button);


        InsertRowRecyclerAdapter adapter = new InsertRowRecyclerAdapter(this,TableLookActivity.tableFieldArrayList);
        recyclerView.setAdapter(adapter);


        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InsertRowActivity.this.finish();
            }
        });
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}