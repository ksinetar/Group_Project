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
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchGroupInfo extends Activity implements Button.OnClickListener {

    private TextView textViewGroupName, textViewDescription, textViewCategory, textViewMemberCount;
    private Button buttonJoinGroup;

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group_info);

        textViewGroupName = (TextView) findViewById(R.id.textViewGroupName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewCategory = (TextView) findViewById(R.id.textViewCategory);
        textViewMemberCount = (TextView) findViewById(R.id.textViewMemberCount);

        buttonJoinGroup = (Button) findViewById(R.id.buttonJoinGroup);
        buttonJoinGroup.setOnClickListener(this);

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

    }
}
