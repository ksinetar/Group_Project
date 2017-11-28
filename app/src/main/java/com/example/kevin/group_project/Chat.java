package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Chat extends Activity {

    private FirebaseListAdapter<ChatMessage> adapter;
    private ListView listOfMessages;
    private String fullName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FloatingActionButton fab =
                (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText input = (EditText)findViewById(R.id.input);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                FirebaseDatabase.getInstance()
                        .getReference()
                        .child("messages")
                        .push()
                        .setValue(new ChatMessage(input.getText().toString(),
                                fullName)
                        );

                // Clear the input
                input.setText("");

            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final String user_id = mAuth.getCurrentUser().getUid();

        DatabaseReference mDatabaseUser_fullname = FirebaseDatabase.getInstance().getReference().child("users").child(user_id).child("fullname");

        mDatabaseUser_fullname.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fullName = dataSnapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        listOfMessages = (ListView) findViewById(R.id.list_of_messages);

        displayChatMessages();
    }

    private void displayChatMessages() {
        Log.d("debugChatMessage", "displayChatMessages() called");


//Error is somewhere in Adapter not working
        Query query = FirebaseDatabase.getInstance().getReference("messages");
        FirebaseListOptions<ChatMessage> options = new FirebaseListOptions.Builder<ChatMessage>()
              .setLayout(R.layout.message)
               .setQuery(query, ChatMessage.class).build();

        adapter = new FirebaseListAdapter<ChatMessage>(options) {

            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messageText = (TextView) v.findViewById(R.id.message_text);
                TextView messageUser = (TextView) v.findViewById(R.id.message_user);
                TextView messageTime = (TextView) v.findViewById(R.id.message_time);

                //Debugging tool
                Log.d("debugChatMessage", "Message: " + model.getMessageText());

                // Set their text
                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());

                // Format the date before showing it
                messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                        model.getMessageTime()));
            }
        };

        //Debugging tool, shouldnt have count = 0
        Log.d("debugChatMessage", "Adapter Count: " + adapter.getCount());

        listOfMessages.setAdapter(adapter);
    }
}
