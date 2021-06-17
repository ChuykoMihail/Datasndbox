package com.example.datasndbox10;

import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.example.datasndbox10.ForeignKeyActivity.ForeignKeyActivity;

import java.util.ArrayList;
public class EditColumnActivity extends AppCompatActivity {



    Button foreignButton;
    Button saveButton;
    String constraint;
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
        foreignButton = findViewById(R.id.save_foreign_key_button);
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
                String command = RecyclerAdapter.toSQLEditColumns.generateSQL(adapter.fields, EditColumnActivity.this);
                if(constraint!=null){
                    MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName.getText().toString()+" ("+command+", "+ constraint+")");
                } else{
                    MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName.getText().toString()+" ("+command+")");
                }

            }
        });

        foreignButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.FKname.length()>0){
                    Intent intent = new Intent(EditColumnActivity.this, ForeignKeyActivity.class);
                    intent.putExtra("FKfield", adapter.FKname);
                    startActivityForResult(intent,1);
                } else {
                    Toast.makeText(EditColumnActivity.this,"нет внешних ключей",Toast.LENGTH_LONG);
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}
        constraint= data.getStringExtra("constraint");
    }
}
