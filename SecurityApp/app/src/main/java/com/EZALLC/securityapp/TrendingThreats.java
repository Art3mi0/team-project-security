package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TrendingThreats extends AppCompatActivity {
    private EditText threatLevel;
    // Can't run network code on main thread :(
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_threats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        threatLevel = findViewById(R.id.threatLevel);
        try {
            getThreatLevel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setTitle("Trending Threats");



    }
    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            Integer result = 0;
            String response="";
            int statusCode = 10;
            try {
                /* forming th java.net.URL object */
                URL url = new URL("http://www.miltonstart.com/ThreatExplanation.aspx");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    response = convertInputStreamToString(inputStream);
                    result = 1; // Successful
                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            return response + statusCode; //"Failed to fetch data!";
        }
        @Override
        protected void onPostExecute(String result) {
            /* Download complete. Update UI */
            String threatString = "AlertCon Level: ";
            String str = "Temp</font></td><td width=\"30\"><font color=\"Black\">";
            String htmlPage = result;
            try {
                int index1 = htmlPage.indexOf(str)+51;
                threatString += htmlPage.charAt(index1);
            }
            catch (Exception ex) {
                threatString+= "Unable to find threat level";
            }
            threatLevel.setText(threatString);
        }
    }


    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }

    public void getThreatLevel() throws Exception {
        /**
         * Takes no input and retrieves threat level from IBM X-Force API
         * @return A string of the threat level
         */

        new AsyncHttpTask().execute();
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