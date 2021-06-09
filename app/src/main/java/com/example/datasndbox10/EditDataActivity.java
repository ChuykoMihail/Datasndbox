package com.example.datasndbox10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditDataActivity extends AppCompatActivity {


    Spinner typeSpinner;
    ArrayAdapter<String> adapter;
    Button saveButton;
    EditText dataText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getIntent().getExtras();

        setContentView(R.layout.content_refractor);
        String[] types = getResources().getStringArray(R.array.types_of_data);
        dataText = findViewById(R.id.edit_data_editText);
        typeSpinner = findViewById(R.id.data_type_spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);

        dataText.setText(arguments.get("data").toString());

    }
}