package com.example.datasndbox10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class InsertRowActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button SaveButton;
    Button CancelButton;
    public ArrayList<TableField> DataForSaving = new ArrayList<TableField>();
    ArrayList<JustInserRowTextClass> data = new ArrayList<JustInserRowTextClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_row);


        recyclerView =findViewById(R.id.insert_new_row_recyclerView);
        SaveButton = findViewById(R.id.insert_new_row_button);
        CancelButton = findViewById(R.id.insert_new_row_cancel_button);

        setInitialData();
        InsertRowRecyclerView adapter = new InsertRowRecyclerView(InsertRowActivity.this,data);
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
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i< DataForSaving.size(); i++){
                    contentValues.put(DataForSaving.get(i).getFlagResource(),data.get(i).getDataForInsert());
                }
                if(MainActivity.db.insert(TableLookActivity.tableName,null,contentValues) == -1)
                    Toast.makeText(InsertRowActivity.this,"ошибка", Toast.LENGTH_LONG);

            }
        });



    }
    private void setInitialData(){

        for(TableField i: TableLookActivity.tableFieldArrayList){
            data.add(new JustInserRowTextClass(i.getType()));
            DataForSaving.add(i);
        }

    }
}