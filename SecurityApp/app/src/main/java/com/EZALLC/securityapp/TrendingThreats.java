package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class TrendingThreats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_threats);
    }
    public String getThreatLevel() {
        /**
         * Takes no input and retrieves threat level from IBM X-Force API
         * @return A string of the threat level
         */
        return "No threat... for now";
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
}