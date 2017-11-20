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
import com.google.firebase.database.DatabaseReference;

public class Profile extends Activity implements Button.OnClickListener {

    private EditText editTextName, editTextUniversity, editTextCity, editTextDateofbirth;
    private Button buttonSignUp;
    private Spinner spinnerGender;

    private String email;
    private String name;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Put on Signup 2
//        Intent Next = new Intent(this, SignUp2.class);
//        Next.putExtra("EMAIL", editTextEmail.getText().toString());
//        this.startActivity(Next);


//        use with from
//        Intent intent =getIntent();
//        email = intent.getStringExtra("EMAIL");

        editTextName = findViewById(R.id.editTextName);


        editTextUniversity = findViewById(R.id.editTextUniversity);

        editTextCity = findViewById(R.id.editTextCity);

        editTextDateofbirth = findViewById(R.id.editTextDateofbirth);

        buttonSignUp = findViewById(R.id.buttonSignUp);





        buttonSignUp.setOnClickListener(this);

        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(Profile.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);
    }

    @Override
    public void onClick(View view) {

    }
}
