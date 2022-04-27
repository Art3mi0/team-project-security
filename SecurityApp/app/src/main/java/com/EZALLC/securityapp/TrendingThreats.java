package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class TrendingThreats extends AppCompatActivity {
    private TextView threatLevel;
    private ListView threatList;
    // Can't run network code on main thread :(
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_threats);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Trending Threats");

        threatLevel = findViewById(R.id.threatLevel);
        threatList = findViewById(R.id.threatList);

        try {
            getThreatLevel();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getTopThreats();

        } catch (Exception e) {
            e.printStackTrace();
        }



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
    public class AsyncThreatTask extends AsyncTask<String, Void, String> {
        private ArrayAdapter arrayAdapter;
        private ArrayList<String> ips;
        @Override
        protected String doInBackground(String... params) {
            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;
            Integer result = 0;
            String response="";
            int statusCode = 10;
            try {
                /* forming th java.net.URL object */
                URL url = new URL("https://www.blocklist.de/en/statistics.html");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    response = convertInputStream(inputStream);
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
            String htmlPage = result;
            ArrayList<String> ips = new ArrayList<String>();
            int start = 0;
            int end = 0;
            for (int i = 0; i< htmlPage.length()-26; i++) {
                if (htmlPage.substring(i,i+26).contains("<a href=\"/en/view.html?ip=")) {
                    start = i+26;
                    int index = htmlPage.substring(start, start+100).indexOf('"');
                    end = index + start;
                    System.out.println(htmlPage.substring(start,end));
    			ips.add(htmlPage.substring(start,end));
                }
            }
//    	System.out.println(content);
//    	System.out.println(getThreatLevel(content));
//            String threatLevel = "";
//    	String str = "Temp</font></td><td width=\"30\"><font color=\"Black\">";
//    	String htmlPage = content;
//    	int index1 = htmlPage.indexOf(str)+51;
//    	threatLevel += htmlPage.charAt(index1);
//        System.out.println(threatLevel);
            for (int i = 0; i< ips.size(); i++) {
                Log.i("IP list entry", ips.get(i));

            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TrendingThreats.this, android.R.layout.simple_list_item_1,ips);
            threatList.setAdapter(arrayAdapter);
            AdapterView.OnItemClickListener itemClickListener =
                    new AdapterView.OnItemClickListener() {
                        // @Override
                        public void onItemClick(AdapterView<?> listView, View view, int i, long l) {
                            String item = ips.get(i);
                            Intent intent = new Intent(TrendingThreats.this,IPHashInfo.class);
                            intent.putExtra("key",item);
                            startActivity(intent);
//                            Toast.makeText(TrendingThreats.this,item, Toast.LENGTH_SHORT).show();
                        }
                    };
            threatList.setOnItemClickListener(itemClickListener);
        }
    }


    private String convertInputStream(InputStream inputStream) throws IOException {

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
    public void getTopThreats() {
        /**
         * Takes no input and retrieves top threats from IBM X-Force API
         * @return A arraylist of strings that represent each threat
         */
        new AsyncThreatTask().execute();
//        ArrayList<String> noError = new ArrayList<String>();
//        return noError;
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