package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends Activity implements Button.OnClickListener {

    private EditText editTextName, editTextCity, editTextUniversity, editTextGender, editTextDateofbirth;
    private Button buttonUpdate;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextUniversity = (EditText) findViewById(R.id.editTextUniversity);
        editTextDateofbirth = (EditText) findViewById(R.id.editTextDateofbirth);
        editTextGender = (EditText) findViewById(R.id.editTextGender);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(this);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = db.getReference("users");
        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Toast.makeText(Profile.this, "User Updated!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("users").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Users Lookup = dataSnapshot.getValue(Users.class);
                editTextName.setText(Lookup.fullname);
                editTextCity.setText(Lookup.city);
                editTextDateofbirth.setText(Lookup.dateofBirth);
                editTextGender.setText(Lookup.gender);
                editTextUniversity.setText(Lookup.university);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Add Menu Functionality
        MenuInflater mainMenuInflater = getMenuInflater();
        mainMenuInflater.inflate(R.menu.mainmenu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_home) {
            Intent intentHome = new Intent(this, Home.class);
            this.startActivity(intentHome);
        } else if (item.getItemId() == R.id.menu_create_group) {
            Intent intentCreate = new Intent(this, CreateGroup.class);
            this.startActivity(intentCreate);
        } else if (item.getItemId() == R.id.menu_profile) {
            Intent intentProfile = new Intent(this, Profile.class);
            this.startActivity(intentProfile);
        } else if (item.getItemId() == R.id.menu_search) {
            Intent intentSearch = new Intent(this, Search.class);
            this.startActivity(intentSearch);
        } else if (item.getItemId() == R.id.menu_log_out) {
            Intent intentLogOut = new Intent(this, Login.class);
            this.startActivity(intentLogOut);
        } return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = db.getReference("users");

        if (editTextName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Specify Updated Name", Toast.LENGTH_SHORT).show();
        } else if (editTextGender.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Specify Updated Gender", Toast.LENGTH_SHORT).show();
        } else if (editTextDateofbirth.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Specify Updated Date of Birth", Toast.LENGTH_SHORT).show();
        } else if (editTextUniversity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Specify Updated University", Toast.LENGTH_SHORT).show();
        } else if (editTextCity.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please Specify Updated City", Toast.LENGTH_SHORT).show();
        } else {

            final String newName = editTextName.getText().toString();
            final String newGender = editTextGender.getText().toString();
            final String newDateofbirth = editTextDateofbirth.getText().toString();
            final String newUniversity = editTextUniversity.getText().toString();
            final String newCity = editTextCity.getText().toString();

            userRef.orderByChild("fullname").equalTo(newName).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) {
                        Toast.makeText(Profile.this, "User Not Found", Toast.LENGTH_SHORT).show();
                    } else {
                        for (DataSnapshot user : dataSnapshot.getChildren()) {
                            String userKey = user.getKey();
                            userRef.child(userKey).child("gender").setValue(newGender);
                            userRef.child(userKey).child("dateofBirth").setValue(newDateofbirth);
                            userRef.child(userKey).child("university").setValue(newUniversity);
                            userRef.child(userKey).child("city").setValue(newCity);
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}

