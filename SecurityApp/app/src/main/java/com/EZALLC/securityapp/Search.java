package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.core.View;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    private Button searchButton;
    private EditText searchUserInput;
    private String ipORFile;
    private ScrollView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //done immediately

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        searchView = findViewById(R.id.searchScrollView);
        searchUserInput= findViewById(R.id.searchInput);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    /**
     * This onSearch method receives users file hash or IP address
     * and displays on page
     * @param view listener for selection and string with IP or File hash
     * @return arraylist with String IP or FileHash and initial input
     */
    public void onSearch(View view, String IpOrFile){
        ipORFile = searchUserInput.getText().toString();
        if(ipORFile.isEmpty()){
            Toast.makeText(Search.this, "Enter a IP address or file Hash", Toast.LENGTH_SHORT).show();
        }

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