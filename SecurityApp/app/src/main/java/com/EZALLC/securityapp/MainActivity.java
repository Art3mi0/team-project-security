package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


