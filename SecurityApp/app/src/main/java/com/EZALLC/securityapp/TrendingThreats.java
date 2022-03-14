package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.EditText;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class TrendingThreats extends AppCompatActivity {
    private EditText threatLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_threats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_trending_threats);

        threatLevel = findViewById(R.id.threatLevel);
//        String stringThreatLevel = getThreatLevel();
        threatLevel.setText("Threat Level: ");
        getSupportActionBar().setTitle("Trending Threats");
//        setContentView(R.layout.activity_trending_threats);
//        TextView threatLevel = findViewById(R.id.textView4);
//        TextView threatLevel = (TextView) findViewById(R.id.textView4);
//        threatLevel.setText("Test");
//
//        threatLevel.setText(getThreatLevel());

    }
    public String getThreatLevel() {
        /**
         * Takes no input and retrieves threat level from IBM X-Force API
         * @return A string of the threat level
         */
        String content = null;
        URLConnection connection = null;
        String threatString = "";
        try {
            connection =  new URL("http://www.miltonstart.com/ThreatExplanation.aspx").openConnection();
            Scanner scanner = new Scanner(connection.getInputStream());
            scanner.useDelimiter("\\Z");
            content = scanner.next();
            scanner.close();
        }catch ( Exception ex ) {
            threatString +="Could not retrieve threat level.";
        }

        String str = "Temp</font></td><td width=\"30\"><font color=\"Black\">";
        String htmlPage = content;
        int index1 = htmlPage.indexOf(str)+51;
        threatString += htmlPage.charAt(index1);
        return threatString;
    }
    public ArrayList<String> getTopThreats() {
        /**
         * Takes no input and retrieves top threats from IBM X-Force API
         * @return A arraylist of strings that represent each threat
         */
        ArrayList<String> noError = new ArrayList<String>();
        return noError;
    }
    public void addToList(String threat) {
        /**
         * Takes a threat as a string and uses the IP and File Hash List API to update the database
         * @param threat Some string representing either a file hash or IP address.
         */
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