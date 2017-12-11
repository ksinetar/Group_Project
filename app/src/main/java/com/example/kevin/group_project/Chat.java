package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class Chat extends Activity implements View.OnClickListener {

    private EditText editTextChat;
    private Button buttonSubmit;
    private ListView listViewFire;
    private String Group;
    private String Fullname;

    ArrayList<ChatMessage> list = new ArrayList<>();
    ArrayAdapter<ChatMessage> adapter;

    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent =getIntent();
        Group = intent.getStringExtra("Group");
        Fullname = intent.getStringExtra("Fullname");

        editTextChat = findViewById(R.id.editTextName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        listViewFire = findViewById(R.id.listViewFire);

        buttonSubmit.setOnClickListener(this);

//        adapter = new ArrayAdapter<ChatMessage>(this, R.layout.chat_layout, R.id.messageText, list);
        adapter = new ArrayAdapter (this, R.layout.chat_layout, R.id.messageText, list)
        {
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text1 = (TextView) view.findViewById(R.id.messageText);
                TextView text2 = (TextView) view.findViewById(R.id.messageSender);
                TextView text3 = (TextView) view.findViewById(R.id.messageTime);

                text1.setText(list.get(position).getMessageText());
                text2.setText(list.get(position).getMessageUser());
                text3.setText(list.get(position).getMessageTime() + "");
                return view;
            }
        };
        listViewFire.setAdapter(adapter);



        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Change this to match our structure
        DatabaseReference myRef = database.getReference("chat");

        //Add this in when testing stuff is removed
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //Need it to view p the same way it views myRef
        DatabaseReference p = mFirebaseDatabase.child("groups").child(Group).child("messages");

        //Change this to p?
        p.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                //Testing stuff
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

                String fullname = mFirebaseDatabase.child("users").child(uid).child("fullname").toString();

                ChatMessage chat;
                chat = dataSnapshot.getValue(ChatMessage.class);
                list.add(chat);


                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        place = mFirebaseDatabase.child("groups").child(Group).child("messages");
        place.push().setValue(new ChatMessage(editTextChat.getText().toString(),Fullname));

        editTextChat.setText("");
    }

    //Use this to convert date for display
    //String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));
}
