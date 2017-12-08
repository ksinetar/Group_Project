package com.example.kevin.group_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class SignUp extends Activity implements Button.OnClickListener {

    private EditText editTextEmail, editTextPassword;

    private Button buttonNext;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mDatabase;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextEmail = findViewById(R.id.editTextDescription);

        buttonNext = findViewById(R.id.buttonNext);

        buttonNext.setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged:signed_in" + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

    }

    public void createAccount(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());


                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Authentication Failed",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, "Registration Successful",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonNext:

                if (!editTextEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    editTextEmail.setError("Invalid Email Address");

                } else if (editTextPassword.getText().length() < 6) {
                    editTextPassword.setError("Password Must Be At Least 6 Characters");

                } else {
                    createAccount(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                    Intent Next = new Intent(this, SignUp2.class);
                    Next.putExtra("EMAIL", editTextEmail.getText().toString());
                    this.startActivity(Next);

                    break;
                }
        }
    }
}