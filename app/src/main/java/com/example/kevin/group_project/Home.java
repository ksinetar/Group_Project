package com.example.kevin.group_project;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private EditText editTextGroups;
    private Button buttonGoGroups;
    private ListView listViewGroups;
    private TextView textViewGroups;

    private String fullname;

    ArrayList<String> listGroup = new ArrayList<>();
    ArrayAdapter<String> adapterGroup;

    private DatabaseReference mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ActionBar actionBar = getActionBar();

        // actionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // titleTextView
        TextView titleTextView = new TextView(actionBar.getThemedContext());
        titleTextView.setText("Home");
        titleTextView.setPadding(60, 30, 0, 0);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(1, 18);

        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2965C9")));

//        editTextGroups = findViewById(R.id.editTextGroups);
//        buttonGoGroups = findViewById(R.id.buttonGoGroups);
        listViewGroups = findViewById(R.id.listViewGroups);
        listViewGroups.setOnItemClickListener(this);

//        buttonGoGroups.setOnClickListener(this);


        // Write a message to the database
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Change this to match our structure
        DatabaseReference myRef = database.getReference("users").child(mAuth.getCurrentUser().getUid()).child("groups");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot group : dataSnapshot.getChildren()) {
                    listGroup.add(group.child("grouplist").getValue().toString());
                }
                adapterGroup = new ArrayAdapter<String>(Home.this, R.layout.grouplayout, R.id.textViewGroups, listGroup);
                listViewGroups.setAdapter(adapterGroup);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Change this to match our structure
        DatabaseReference userRef = database.getReference("users").child(mAuth.getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullname = dataSnapshot.child("fullname").getValue().toString();
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("groups");

        myRef.push().setValue(editTextGroups.getText().toString());
        editTextGroups.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent gotoChat = new Intent(Home.this, Chat.class);
        gotoChat.putExtra("Group", listGroup.get(position));
        gotoChat.putExtra("Fullname", fullname);
        this.startActivity(gotoChat);
    }
}
