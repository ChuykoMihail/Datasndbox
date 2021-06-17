package com.example.datasndbox10;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // набор данных, которые свяжем со списком
    public static ArrayList<String> tablesNames;
    public static SQLiteDatabase db;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tablesNames = new ArrayList<String>();
        db = getBaseContext().openOrCreateDatabase("datasandbox.db", MODE_PRIVATE, null);
        db.setForeignKeyConstraintsEnabled(true);




        // получаем элемент ListView
        ListView table_list = (ListView) findViewById(R.id.table_list);
        Button createNewTable = findViewById(R.id.create_button);



        // создаем адаптер
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, tablesNames);

        // устанавливаем для списка адаптер
        table_list.setAdapter(adapter);

        table_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TableLookActivity.class);
                intent.putExtra("tableName", tablesNames.get(position));
                startActivity(intent);
            }
        });
        table_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Удалить выбранную таблицу?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                db.execSQL("DROP TABLE IF EXISTS "+tablesNames.get(position));
                                tablesNames.clear();
                                tablesNames.add("пустышка");
                                Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

                                if (c.moveToFirst()) {
                                    while (!c.isAfterLast()) {
                                        tablesNames.add(c.getString(c.getColumnIndex("name")));
                                        c.moveToNext();
                                    }
                                }
                                adapter.notifyDataSetChanged();

                            }
                        }).setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Думай лучше", Toast.LENGTH_LONG);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });

        createNewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditColumnActivity.class);
                startActivity(intent);
                //передаём NULL
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        tablesNames.clear();
        tablesNames.add("пустышка");
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                tablesNames.add(c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }

        adapter.notifyDataSetChanged();
    }
}