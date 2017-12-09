package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateGroup extends Activity implements Button.OnClickListener, AdapterView.OnItemSelectedListener{

    private Button buttonCreateGroup;
    private EditText editTextGroupName, editTextDescription;
    private DatabaseReference mFirebaseDatabase;
    private Spinner spinnerCategory;
    private TextView textViewName, textViewDescription, textViewCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

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

        if (editTextGroupName.getText().toString().isEmpty()) { // this is all validation that the editTexts are filled
            Toast.makeText(CreateGroup.this, "Please Fill Out All Fields",
                    Toast.LENGTH_SHORT).show();
        } else if (editTextDescription.getText().toString().isEmpty()) {
            Toast.makeText(CreateGroup.this, "Please Fill Out All Fields",
                    Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.buttonCreateGroup) { // these start a method if u click the corresponding (button)
            createGroup();
        }
    }

    private void createGroup(){ // pushes to firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("groups").push().child("info").setValue(newGroup());
        Toast.makeText(CreateGroup.this, "Group Created Successfully",
                Toast.LENGTH_SHORT).show();

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
