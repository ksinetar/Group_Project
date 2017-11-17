package com.example.kevin.group_project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChatMemberProfile extends Activity implements View.OnClickListener {

    private TextView textViewPicture, textViewMemberProfileName, textViewMPNameFB, textViewMPBio, textViewMPBioFB, textViewMPBirthday, textViewMPBirthdayFB, textViewMPEducation, textViewMPEducationFB, textViewMPResidence, textViewMPResidenceFB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_member_profile);

        textViewMemberProfileName = findViewById(R.id.textViewMemberProfileName);
        textViewMPBio = findViewById(R.id.textViewMPBio);
        textViewMPBioFB = findViewById(R.id.textViewMPBioFB);
        textViewMPBirthday = findViewById(R.id.textViewMPBirthday);
        textViewMPBirthdayFB = findViewById(R.id.textViewMPBirthdayFB);
        textViewMPEducation = findViewById(R.id.textViewMPEducation);
        textViewMPEducationFB = findViewById(R.id.textViewMPEducationFB);
        textViewMPNameFB = findViewById(R.id.textViewMPNameFB);
        textViewMPResidence = findViewById(R.id.textViewMPResidence);
        textViewMPResidenceFB = findViewById(R.id.textViewMPResidenceFB);
        textViewPicture = findViewById(R.id.textViewPicture);

    }

    @Override
    public void onClick(View v) {

    }
}
