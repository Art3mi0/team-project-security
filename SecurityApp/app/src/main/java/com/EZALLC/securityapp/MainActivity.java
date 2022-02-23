package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Homebase");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creates the options menu on start
        // Should only be called once
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iphash:
                // User chose the "Settings" item, show the app settings UI...
                Intent myIntent = new Intent(this, IPHashInfo.class);
                startActivity(myIntent);
                return true;

            case R.id.trendingthreats:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent myIntent2 = new Intent(this, TrendingThreats.class);
                startActivity(myIntent2);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * this function randomly generates passwords for the user to use
     * @param view = button click
     * Output = an error or a new password
     */
    public void onGeneratePassword(View view) {
        return;

    }

    /**
     * this function uses an API to check if an email has been compromised or not
     * @param view = user input and button click
     * Output = a pop up that says error or email compromised or not
     */
    public void onCheckEmail(View view) {
        return;

    }
}