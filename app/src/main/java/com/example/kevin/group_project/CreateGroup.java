package com.example.kevin.group_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CreateGroup extends Activity implements Button.OnClickListener, AdapterView.OnItemSelectedListener{

    private Button buttonCreateGroup;
    private EditText editTextGroupName, editTextDescription;
    private DatabaseReference mFirebaseDatabase;
    private Spinner spinnerCategory;
    private TextView textViewName, textViewDescription, textViewCategory;

    private String groupname, foundName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        final ActionBar actionBar = getActionBar();

        // actionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // titleTextView
        TextView titleTextView = new TextView(actionBar.getThemedContext());
        titleTextView.setText("Create Group");
        titleTextView.setPadding(60, 30, 0, 0);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(1, 18);

        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2965C9")));

        buttonCreateGroup = findViewById(R.id.buttonCreateGroup);
        buttonCreateGroup.setOnClickListener(this);

        editTextGroupName = findViewById(R.id.editTextGroupName);
        editTextDescription = findViewById(R.id.editTextDescription);

        spinnerCategory = findViewById(R.id.spinnerCategory);
        spinnerCategory.setOnItemSelectedListener(this);

//        ArrayList<String> stringArr = new ArrayList<>();
//        stringArr.add("apple");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, stringArr);
//        spinnerCategory.setAdapter(adapter);

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

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference groupRef = db.getReference("groups");

        final String findGroup = editTextGroupName.getText().toString();

        if (editTextGroupName.getText().toString().isEmpty()) { // this is all validation that the editTexts are filled
            Toast.makeText(CreateGroup.this, "Please Fill Out All Fields",
                    Toast.LENGTH_SHORT).show();
        } else if (editTextDescription.getText().toString().isEmpty()) {
            Toast.makeText(CreateGroup.this, "Please Fill Out All Fields",
                    Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.buttonCreateGroup) { // these start a method if u click the corresponding (button)
            groupRef.child(findGroup).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() != null) {
                        Toast.makeText(CreateGroup.this, "Group Name Already Exists", // for some reason this happens all the time
                                Toast.LENGTH_SHORT).show();
                    } else if (dataSnapshot.getValue() == null) {
                        createGroup();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void createGroup(){ // pushes to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("groups").child(editTextGroupName.getText().toString()).child("info").setValue(newGroup());
        Toast.makeText(CreateGroup.this, "Group Created Successfully",
                Toast.LENGTH_SHORT).show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();
        groupname = editTextGroupName.getText().toString();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                foundName = dataSnapshot.child("fullname").getValue().toString();
                mFirebaseDatabase.child("groups").child(groupname).child("members").push().child("membername").setValue(foundName); // pushes user to groups directory
                mFirebaseDatabase.child("users").child(uid).child("groups").push().child("grouplist").setValue(groupname); // pushes group to users directory
                // ideally, we want to include an IF statement to ensure there are no duplicate members, but unsure how to deal with users that have same "fullname" (given current structure)
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Intent takeHome = new Intent(this, Home.class);
        this.startActivity(takeHome);
    }

    private String itemClicked;

    @Override // gets current spinner selection
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        if (position == 0) {
        } else {
            String[] stringArr = this.getResources().getStringArray(R.array.category_arrays);
            itemClicked = stringArr[position];
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private Group newGroup() {
        return new Group(editTextGroupName.getText().toString(), editTextDescription.getText().toString(), itemClicked);
    }

}
