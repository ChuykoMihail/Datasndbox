package com.example.datasndbox10.ForeignKeyActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.datasndbox10.MainActivity;
import com.example.datasndbox10.R;

import java.util.ArrayList;

public class ForeignKeyActivity extends AppCompatActivity {

    Button saveButton;

    ListView tablesListView;
    TextView result;
    String forResult = "";
    String FKname;
    public static ArrayList<String> tablesNames= new ArrayList<String>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foreign_key);
        Intent intent = getIntent();
        FKname = intent.getStringExtra("FKfield");
        tablesListView = findViewById(R.id.list_of_foreign_key_tables);
        result = findViewById(R.id.text_view_foreign_key);
        saveButton = findViewById(R.id.save_foreign_key_button);

        Cursor c = MainActivity.db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                tablesNames.add(c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }


        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, tablesNames);

        tablesListView.setAdapter(adapter);

        tablesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                forResult="";
                forResult += tablesNames.get(position);
                result.setText("FOREIGN KEY ("+FKname+") REFERENCES "+forResult+"(id)");
                forResult = "FOREIGN KEY ("+FKname+") REFERENCES "+forResult+"(id)";
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.putExtra("constraint",forResult);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });




    }


    protected void onResume() {
        super.onResume();

    }
}