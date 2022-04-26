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
        ListViewSearch = findViewById(R.id.SearchList);
        SearchArray = new ArrayList<>();
        SearchAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, SearchArray);
        Intent PageIPHashing = new Intent(Search.this,IPHashInfo.class);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(android.view.View v){
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
                    ListViewSearch.setVisibility(android.view.View.INVISIBLE);
                    Intent intent = new Intent(Search.this,IPHashInfo.class);
                    if(isValidIPAddress(searchUserInput.getText().toString())==true){
                        String[] vaildIP = {searchUserInput.getText().toString(), "IP"};
                        intent.putExtra("SendIP",vaildIP);
                    }
                    else if(validURl(searchUserInput.getText().toString())==true){
                        String[] URL = {searchUserInput.getText().toString(), "URL"};
                        intent.putExtra("SendURL",URL);
                    }
                    Search.this.startActivity(PageIPHashing);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ListViewSearch.setVisibility(android.view.View.VISIBLE);
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
                onSearch(SearchArray.get(i));
                searchButton.setEnabled(false);
                ListViewSearch.setVisibility(android.view.View.INVISIBLE);
                Intent intent1 = new Intent(Search.this,IPHashInfo.class);
                if(isValidIPAddress(searchUserInput.getText().toString())==true){
                    String[] vaildIP = {searchUserInput.getText().toString(), "IP"};
                    intent1.putExtra("SendIP",vaildIP);
                }
                else if(validURl(searchUserInput.getText().toString())==true){
                    String[] URL = {searchUserInput.getText().toString(), "URL"};
                    intent1.putExtra("SendURL",URL);
                }
                Search.this.startActivity(PageIPHashing);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ListViewSearch.setVisibility(android.view.View.VISIBLE);
                        searchButton.setEnabled(true);
                        Log.d(TAG, "resend1");

                    }
                }, 6000); }
        });

    }

    /**
     * This isValidIPAddress method receives users URL or IP address
     * and checks if valid ip address in form IPv4
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
     * This validURl method receives users URL or IP address
     * and check if valid URL with domain name including .com,.net,.gov
     * @param URL
     * @return true if valid URL and false if not
     */
    public static boolean validURl(String URL)
    {
        String url
                = "((http|https)://)(www.)?"
                + "[a-zA-Z0-9@:%._\\+~#?&//=]{2,256}\\.(com|net|gov)/{0,1}"
                + "\\b([-a-zA-Z0-9@:%._\\+~#?&//=]*)";
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
     * and displays on page if its valid ip address or url
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