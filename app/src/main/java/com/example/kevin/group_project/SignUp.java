package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends Activity implements Button.OnClickListener {

    private EditText editTextUsername, editTextName, editTextEmail;
    private EditText editTextPassword, editTextUniversity, editTextCity;
    private EditText editTextDateofbirth;

    private Button buttonSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUniversity = findViewById(R.id.editTextUniversity);
        editTextCity = findViewById(R.id.editTextCity);
        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);

        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:

                if ((editTextUsername.getText().toString().isEmpty())) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else if (editTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else if (editTextName.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else if (editTextEmail.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else if (editTextUniversity.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else if (editTextCity.getText().toString().isEmpty()) {
                    Toast.makeText(SignUp.this, "Please Fill All Fields",
                            Toast.LENGTH_SHORT).show();
                } else {
                    //Add code for adding provided info to Firebase, then take user to homepage
                }
        }
    }
}