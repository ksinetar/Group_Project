package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SignUp extends Activity {

    private TextView textViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textViewSignUp = findViewById(R.id.textViewSignUp);
    }
}
