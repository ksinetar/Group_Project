package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

    ArrayList<String> list = new ArrayList<>();
    ArrayAdapter<String> adapter;

    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        editTextChat = findViewById(R.id.editTextChat);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        listViewFire = findViewById(R.id.listViewFire);

        buttonSubmit.setOnClickListener(this);

        adapter = new ArrayAdapter<String>(this, R.layout.chat_layout, R.id.textViewChat, list);
        listViewFire.setAdapter(adapter);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        //Change this to match our structure
        DatabaseReference myRef = database.getReference("chat");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String chat;
                chat = dataSnapshot.getValue(String.class);
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

        //Change Daves friends to actual
        mFirebaseDatabase.child("groups").child("Jake's Friends").child("info").child("messages").push()
                .setValue(new ChatMessage(editTextChat.getText().toString(),fullname));

        editTextChat.setText("");


    }
}
