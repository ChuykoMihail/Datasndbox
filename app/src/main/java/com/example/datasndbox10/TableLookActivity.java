package com.example.datasndbox10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class TableLookActivity extends AppCompatActivity {


    String tableName;
    GridView selectedTable;
    Button addColumnButton;
    Button insertRow;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    EditText dataChangeEditText;
    ArrayList<String> data1;

    static public ArrayList<TableField> tableFieldArrayList = new ArrayList<TableField>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);
        //получаем название таблицы
        Intent intent = getIntent();
        tableName = intent.getStringExtra("tableName");


        //Находим элементы интерфейса
        selectedTable = findViewById(R.id.some_table);
        addColumnButton = findViewById(R.id.add_column_button);
        insertRow = findViewById(R.id.add_row_button);



        Cursor infoQuerry = MainActivity.db.rawQuery("SELECT sql FROM sqlite_master WHERE tbl_name = '"+tableName+"';",null);
        infoQuerry.moveToFirst();
        String createStatement = infoQuerry.getString(0);

        StringToTableField conv = new StringToTableField();
        conv.convertToField(createStatement,tableFieldArrayList);
        //получаем данные таблицы
        for(TableField i:tableFieldArrayList){
            data.add(i.getFlagResource().toString());
        }

        Cursor query = MainActivity.db.rawQuery("SELECT * FROM "+tableName+";", null);
        if (query.moveToFirst()) {
            while (!query.isAfterLast()) {
                for (TableField i:tableFieldArrayList){
                    String someStringData;
                    int somneIntData=0;
                    if(i.getType()=="TEXT") someStringData = query.getString(query.getColumnIndex(i.getFlagResource()));

                    else {
                        somneIntData = query.getInt(query.getColumnIndex(i.getFlagResource()));
                        someStringData = valueOf(somneIntData);
                    }
                    data.add(someStringData);
                    query.moveToNext();

                }
            }
        }

        //здесь уже нужно знать структуру таблицы






        adapter = new ArrayAdapter<String>(this, R.layout.item_of_table, R.id.tvText, data);
        selectedTable.setAdapter(adapter);
        selectedTable.setVerticalSpacing(5);
        selectedTable.setHorizontalSpacing(5);
        selectedTable.setNumColumns(tableFieldArrayList.size());

        /*dialog = new Dialog(TableLookActivity.this);
        dialog.setContentView(R.layout.dialog_data_change);
        dialog.setTitle("Изменить данные");
        EditText dataChangeEditText = dialog.findViewById(R.id.change_data_dialog_editText);
        dialog.setPo*/




        insertRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TableLookActivity.this, InsertRowActivity.class);
                startActivity(intent);
            }
        });

        selectedTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });


        addColumnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(TableLookActivity.this, EditColumnActivity.class);
                startActivity(intent);
                //передаём NULL
            }
        });
    }
    /*private void adjustGridView(){
        selectedTable.setVerticalSpacing(5);
        selectedTable.setHorizontalSpacing(5);
        selectedTable.setNumColumns(3);

    }*/

    @Override
    protected void onStop() {
        super.onStop();
        tableFieldArrayList.clear();
    }
}