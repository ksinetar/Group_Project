package com.example.kevin.group_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp2 extends Activity implements Button.OnClickListener {

    private EditText editTextName, editTextUniversity, editTextCity, editTextDateofbirth, editTextGender;
    private Button buttonSignUp;


    private String email, userID;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        final ActionBar actionBar = getActionBar();

        // actionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // titleTextView
        TextView titleTextView = new TextView(actionBar.getThemedContext());
        titleTextView.setText("Sign Up");
        titleTextView.setPadding(60, 30, 0, 0);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(1, 18);

        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2965C9")));

        Intent intent =getIntent();
        email = intent.getStringExtra("EMAIL");

        editTextName = findViewById(R.id.editTextName);
        editTextUniversity = findViewById(R.id.editTextUniversity);
        editTextCity = findViewById(R.id.editTextCity);
        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);

        editTextGender = findViewById(R.id.editTextGender);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                uploadToFirebase();
                Intent GoHome = new Intent(this, Home.class);
                this.startActivity(GoHome);

                break;
        }
    }

    private void uploadToFirebase(){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();
        mFirebaseDatabase.child("users").child(userID).setValue(getUser());
    }

    private Users getUser() {

        return new Users(email, editTextGender.getText().toString(), editTextName.getText().toString(), editTextCity.getText().toString(), editTextUniversity.getText().toString(), editTextDateofbirth.getText().toString());
    }
}