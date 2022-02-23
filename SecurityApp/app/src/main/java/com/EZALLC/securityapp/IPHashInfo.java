package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class IPHashInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphash_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        TextView title = (TextView) findViewById(R.id.demo_title);
        title.setText(R.string.iphashdemo);

    }
    /**
     * this method will call the call the API PulseDive for information on the IP recieved from Search Activity
     * @param view = OnCreate will call this methods with an IP
     * Output = Generate relevant information on the IP to be then displayed in OnCreate
     */
    public void onCallPulseDive(View view) {
        return;

    }

    /**
     * this method will call the call the API VirusTotal for information on the Hash recieved from Search Activity
     * @param view = OnCreate will call this methods with a Hash
     * Output = Generate relevant information on the Hash to be then displayed in OnCreate
     */
    public void onCallVirusTotal(View view) {
        return;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
