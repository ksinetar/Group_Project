package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignUp2 extends Activity implements Button.OnClickListener{

    private EditText editTextName, editTextUniversity, editTextCity, editTextDateofbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        editTextName = findViewById(R.id.editTextName);
        editTextUniversity = findViewById(R.id.editTextUniversity);
        editTextCity = findViewById(R.id.editTextCity);
        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);

        Spinner spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUp2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
