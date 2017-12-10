package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
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

    ArrayList<ChatMessage> list = new ArrayList<>();
    ArrayAdapter<ChatMessage> adapter;

    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference place;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextChat = findViewById(R.id.editTextName);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        listViewFire = findViewById(R.id.listViewFire);

        buttonSubmit.setOnClickListener(this);

        adapter = new ArrayAdapter<ChatMessage>(this, R.layout.chat_layout, R.id.messageText, list);
        listViewFire.setAdapter(adapter);



        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Change this to match our structure
        DatabaseReference myRef = database.getReference("chat");

        //Add this in when testing stuff is removed
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        //Need it to view p the same way it views myRef
        DatabaseReference p = mFirebaseDatabase.child("groups").child("Birds").child("messages");

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
    public void onClick(View view) {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();

        String fullname = mFirebaseDatabase.child("users").child(uid).child("fullname").toString();


        place = mFirebaseDatabase.child("groups").child("Birds").child("messages");
        place.push().setValue(new ChatMessage(editTextChat.getText().toString(),fullname));

        editTextChat.setText("");
    }

    //Use this to convert date for display
    //String date = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000));

}
