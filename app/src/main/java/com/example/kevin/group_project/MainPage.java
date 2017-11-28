package com.example.kevin.group_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;

import com.google.firebase.iid.FirebaseInstanceId;

public class MainPage extends AppCompatActivity implements Button.OnClickListener {

    private TextView mTextMessage;
    private Button buttonChat;
    private Button buttonProfile;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        buttonChat = (Button) findViewById(R.id.buttonChat);
        buttonProfile = (Button) findViewById(R.id.buttonProfile);

        buttonChat.setOnClickListener(this);
        buttonProfile.setOnClickListener(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onClick(View view){
        if (view.equals(buttonChat)){
            Intent intentChat = new Intent(MainPage.this,Chat.class);
            this.startActivity(intentChat);
        } else if (view.equals(buttonProfile)){
            Intent intentProfile = new Intent(MainPage.this, Profile.class);
            this.startActivity(intentProfile);
        }

    }

//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//        // If you want to send messages to this application instance or
//        // manage this apps subscriptions on the server side, send the
//        // Instance ID token to your app server.
//        sendRegistrationToServer(refreshedToken);
//    }
}
