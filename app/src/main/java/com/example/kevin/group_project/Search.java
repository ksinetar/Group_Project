package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Search extends Activity implements Button.OnClickListener {

    private EditText editTextGroup;
    private Button buttonSearch, buttonResult;

    private ListView listViewSearch;

    ArrayAdapter<String> adapter;
    private DatabaseReference mFirebaseDatabase;

    private String exists = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextGroup = (EditText) findViewById(R.id.editTextGroup);

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonResult = (Button) findViewById(R.id.buttonResult);

        buttonSearch.setOnClickListener(this);
        buttonResult.setOnClickListener(this);


        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

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
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.buttonSearch) {

            mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            final DatabaseReference groupnameRef = db.getReference("groups");

            final String findGroup = editTextGroup.getText().toString();

            if (editTextGroup.getText().toString().isEmpty()) { // this is all validation that the editTexts are filled
                Toast.makeText(Search.this, "Fill Out Group Name",
                        Toast.LENGTH_SHORT).show();
                exists = "0";
            } else if (view.getId() == R.id.buttonSearch) { // these start a method if u click the corresponding (button)

                groupnameRef.child(findGroup).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.getValue() == null) {
                            Toast.makeText(Search.this, "No Groups Found",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            buttonResult.setText("See " + editTextGroup.getText().toString() + " Info");
                            exists = "1";

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        } else if (view.getId() == R.id.buttonResult) {
           if (exists == "1") {
               Intent GroupInfo = new Intent(this, SearchGroupInfo.class);
               this.startActivity(GroupInfo);
           } else {

           }
        }
    }
}
