package com.example.kevin.group_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchGroupInfo extends Activity implements Button.OnClickListener {

    private TextView textViewGroupName, textViewDescription, textViewCategory;
    private Button buttonJoinGroup;

    private String groupname, foundName;

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group_info);

        final ActionBar actionBar = getActionBar();

        // actionBar
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // titleTextView
        TextView titleTextView = new TextView(actionBar.getThemedContext());
        titleTextView.setText("Group Information");
        titleTextView.setPadding(60, 30, 0, 0);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTextSize(1, 18);

        // Add titleTextView into ActionBar
        actionBar.setCustomView(titleTextView);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2965C9")));

        Intent intent = getIntent();
        groupname = intent.getStringExtra("Group Name");
       // Toast.makeText(this, groupname, Toast.LENGTH_SHORT).show();

        textViewGroupName = (TextView) findViewById(R.id.textViewGroupName);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewCategory = (TextView) findViewById(R.id.textViewCategory);

        buttonJoinGroup = (Button) findViewById(R.id.buttonJoinGroup);
        buttonJoinGroup.setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        mFirebaseDatabase.child("groups").child(groupname).orderByChild("groupName").equalTo(groupname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //long Count = dataSnapshot.getChildrenCount();
                //long i = 0;
                //Toast.makeText(SearchGroupInfo.this, dataSnapshot.getRef().toString(), Toast.LENGTH_LONG).show();



                for (DataSnapshot Lookup: dataSnapshot.getChildren()) {
                    // if (i == Count-1) {
                    // Toast.makeText(SearchGroupInfo.this, Lookup.getValue(String.class), Toast.LENGTH_SHORT).show();

                    //String test1 = Lookup.child("groupCategory").getValue(String.class);
                    //Toast.makeText(SearchGroupInfo.this, test1, Toast.LENGTH_SHORT).show();




                      // Group GroupLookup = Lookup.getValue(Group.class);
                        textViewGroupName.setText(Lookup.child("groupName").getValue(String.class));
                        textViewDescription.setText(Lookup.child("groupDescription").getValue(String.class));
                        textViewCategory.setText(Lookup.child("groupCategory").getValue(String.class));
//                        break;
                    // }
                    // i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    @Override
    public void onClick(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = user.getUid();

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
        Toast.makeText(this, "Successfully joined " + groupname, Toast.LENGTH_SHORT).show();
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


}
