package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp2 extends Activity implements Button.OnClickListener {

    private EditText editTextName, editTextUniversity, editTextCity, editTextDateofbirth;
    private Button buttonSignUp;
    private Spinner spinnerGender;

    private String email;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        Intent intent =getIntent();
        email = intent.getStringExtra("EMAIL");

        editTextName = findViewById(R.id.editTextName);
        editTextUniversity = findViewById(R.id.editTextUniversity);
        editTextCity = findViewById(R.id.editTextCity);
        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);

        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);

        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SignUp2.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSignUp:
                uploadToFirebase();
                Intent GoHome = new Intent(this, MainPage.class);
                this.startActivity(GoHome);

                break;
        }
    }

    private void uploadToFirebase(){
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabase.child("users").child(mAuth.getCurrentUser().getUid()).setValue(getCurrentUser());
    }

    private Users getCurrentUser() {
        String gender = "Male";
        if (spinnerGender.getSelectedItemPosition() == 0) {
            gender = "Male";
        } else if (spinnerGender.getSelectedItemPosition() == 1) {
            gender = "Female";
        } else {
            gender = "Other";
        }
        return new Users(email, gender, editTextName.getText().toString(), editTextCity.getText().toString(), editTextUniversity.getText().toString(), editTextDateofbirth.getText().toString());
    }
}
