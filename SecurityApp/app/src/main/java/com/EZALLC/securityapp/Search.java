package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.firestore.core.View;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //done immediately
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    /**
     * This onSearch method receives users file hash or IP address and returns an array with
     * type and initial input
     * and displays on page
     * @param view listener for selection and string with IP or File hash
     * @return arraylist with String IP or FileHash and initial input
     */
    public ArrayList<String> onSearch(View view, String IpOrFile){
        ArrayList<String> IpOrFileHash= new ArrayList<String>();
        return IpOrFileHash;
    }
    /**
     * This onSelectWatchlistIpOrFileHash method retrieves the IP address or File Hash string from the database
     * based on selection of watchlist
     * @param view listener for selection
     * @return Ip or File Hash String.
     */
    public String onSelectWatchlistIpOrFileHash(View view){
        return "";
    }
    /**
     * This SendIpOrFileHash  method Send type Ip or File Hash and the IP address or File Hash
     * to IpAndHash information Lookup
     * @param ArrayList with type and Ip or File Hash
     */
    public void SendIpOrFileHash(ArrayList IpOrFileHash){

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