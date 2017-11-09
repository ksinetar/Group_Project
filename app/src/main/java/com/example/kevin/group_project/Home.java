package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Home extends Activity {

    private TextView textViewHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textViewHome = findViewById(R.id.textViewHome);
    }
}
