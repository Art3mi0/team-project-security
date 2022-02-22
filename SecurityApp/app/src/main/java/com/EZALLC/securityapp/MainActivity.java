package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

    /**
     * this function uses an API to check if an email has been compromised or not
     * @param view = user input and button click
     * Output = a pop up that says error or email compromised or not
     */
    public void onCheckEmail(View view) {
        return;

    }


    /**
     * this function randomly generates passwords for the user to use
     * @param view = button click
     * Output = an error or a new password
     */
    public void onGeneratePassword(View view) {
        return;

    }
}


