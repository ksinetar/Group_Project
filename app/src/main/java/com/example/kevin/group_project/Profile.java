package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Activity implements Button.OnClickListener {

    private EditText editTextName, editTextUniversity, editTextCity, editTextDateofbirth, editTextEmail;
    private Button buttonSignUp;
    private Spinner spinnerGender;

    private String email;
    private String fullname;
    private String city;
    private String dateofBirth;
    private String education;
    private String gender;

    private String name;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference myRef;
    private DatabaseReference mDatabaseUser_fullname,mDatabaseUser_email, mDatabaseUser_city, mDatabaseUser_dateofBirth, mDatabaseUser_education, mDatabaseUser_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final String user_id = mAuth.getCurrentUser().getUid();


        editTextName = findViewById(R.id.editTextName);

        mDatabaseUser_fullname = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("fullname");

        mDatabaseUser_fullname.addValueEventListener(new ValueEventListener() {
        @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            editTextName.setText(dataSnapshot.getValue(String.class));
            }
        @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        editTextEmail = findViewById(R.id.editTextEmail);
            mDatabaseUser_email = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("email");

        mDatabaseUser_email.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextEmail.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        editTextUniversity = findViewById(R.id.editTextUniversity);
            mDatabaseUser_education = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("education");

        mDatabaseUser_education.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextUniversity.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        editTextCity = findViewById(R.id.editTextCity);
            mDatabaseUser_city = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("city");

        mDatabaseUser_city.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextCity.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);
            mDatabaseUser_dateofBirth = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("dateofBirth");

        mDatabaseUser_dateofBirth.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                editTextDateofbirth.setText(dataSnapshot.getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(this);

        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Profile.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);}



    @Override
    public void onClick(View view) {

    }
}

