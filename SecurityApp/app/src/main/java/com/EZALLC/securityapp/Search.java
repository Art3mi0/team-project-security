package com.EZALLC.securityapp;

import static android.content.ContentValues.TAG;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;
import com.google.firebase.firestore.core.View;
import java.util.ArrayList;
import java.util.regex.*;


public class Search extends AppCompatActivity {
    private static long mLastClickTime;
    private Button searchButton;
    private EditText searchUserInput;
    private String SearchText;
    private ScrollView searchView;
    private ListView ListViewSearch;
    public ArrayAdapter<String> SearchAdapter;
    ArrayList<String> SearchArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //done immediately
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search");
        searchUserInput= findViewById(R.id.searchInput);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /***
         *
         */
        ListViewSearch = findViewById(R.id.SearchList);
        SearchArray = new ArrayList<>();
        SearchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, SearchArray);
        searchUserInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ListViewSearch.setVisibility(android.view.View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        /***
         *
         */
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(android.view.View v){
                ListViewSearch.setVisibility(android.view.View.INVISIBLE);
                if (searchUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(Search.this, "Enter a IP address or URL", Toast.LENGTH_SHORT).show();
                    searchButton.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            searchButton.setEnabled(true);
                            Log.d(TAG, "resend1");

                        }
                    }, 2000);
                }
                else if (isValidIPAddress(searchUserInput.getText().toString())==false && validURl(searchUserInput.getText().toString())==false) {
                    Toast.makeText(Search.this, "Enter a IP address or URL", Toast.LENGTH_SHORT).show();
                    searchButton.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            searchButton.setEnabled(true);
                            Log.d(TAG, "resend1");

                        }
                    }, 2000);
                }
                else{
                    onSearch(searchUserInput.getText().toString());
                    searchButton.setEnabled(false);
                    searchUserInput.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            searchUserInput.setEnabled(true);
                            searchButton.setEnabled(true);
                            Log.d(TAG, "resend1");

                        }
                    }, 6000);
                    }

            }

        });
        ListViewSearch.setAdapter(SearchAdapter);
        ListViewSearch.setVisibility(android.view.View.VISIBLE);
        ListViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                ListViewSearch.setVisibility(android.view.View.INVISIBLE);
                onSearch(SearchArray.get(i));
                searchButton.setEnabled(false);
                searchUserInput.setEnabled(false);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        searchUserInput.setEnabled(true);
                        searchButton.setEnabled(true);
                        Log.d(TAG, "resend1");

                    }
                }, 6000);

            }
        });
    }


    /**
     * This isValidIPAddress method receives users file hash or IP address
     * and check if valid ip address
     * @param  ip
     * @return true if ip address and false if not
     */
    public static boolean isValidIPAddress(String ip)
    {
        String zeroTo255
                = "(\\d{1,2}|(0|1)\\"
                + "d{2}|2[0-4]\\d|25[0-5])";

        String regex
                = zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255 + "\\."
                + zeroTo255;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(ip);
        if(m.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This validFileHash method receives users file hash or IP address
     * and check if valid file hash in the form SHA256,MD5 and SHA1
     * @param URL
     * @return true if valid URL and false if not
     */
    public static boolean validURl(String URL)
    {
        String url
                = "((http|https)://)(www.)?"
        + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.[a-z]"
        + "{2,6}\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
        Pattern strURL = Pattern.compile(url);
        Matcher m = strURL.matcher(URL);
        if(m.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * Add to vaild ip and urls to front of Search history
     * removes all searches after ten searches
     */
    public void add_clear_list(String ipOrUrlAdd){
        if(SearchArray.size()<10){
            SearchArray.add(0,ipOrUrlAdd);
        }
        else{
            SearchArray.clear();
            SearchArray.add(ipOrUrlAdd);
        }
        ListViewSearch.setAdapter(SearchAdapter);
    }
    /**
     * This onSearch method receives users file hash or IP address
     * and displays on page
     * @param   ipOrURL
     */
    public void onSearch(String ipOrURL){
            if(isValidIPAddress(ipOrURL)){
                add_clear_list(ipOrURL);
                Toast.makeText(Search.this, "Valid Ip Address "+ipOrURL+"\nSearching", Toast.LENGTH_SHORT).show();

            }
            else if(validURl(ipOrURL)){
                add_clear_list(ipOrURL);
                Toast.makeText(Search.this, "Valid URL "+ipOrURL+"\nSearching", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(Search.this, "Enter a IP address or URL", Toast.LENGTH_SHORT).show();
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
     *  with type and Ip or File Hash
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