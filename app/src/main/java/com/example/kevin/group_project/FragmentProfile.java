package com.example.kevin.group_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfile extends Fragment implements Button.OnClickListener {

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






    public FragmentProfile() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_profile, container, false);


        final String user_id = mAuth.getCurrentUser().getUid();


        editTextName = (EditText)view.findViewById(R.id.editTextName);

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

        editTextEmail = (EditText)view.findViewById(R.id.editTextEmail);
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

        editTextUniversity = (EditText)view.findViewById(R.id.editTextUniversity);
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


        editTextCity = (EditText)view.findViewById(R.id.editTextCity);
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


        editTextDateofbirth = (EditText)view.findViewById(R.id.editTextDateofbirth);
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

        buttonSignUp = (Button)view.findViewById(R.id.buttonSignUp);

        buttonSignUp.setOnClickListener(this);

        spinnerGender = (Spinner)view.findViewById(R.id.spinnerGender);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Gender));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(myAdapter);
        return inflater.inflate(R.layout.fragment_fragment_profile, container, false);
    }



    @Override
    public void onClick(View view) {

    }
}
