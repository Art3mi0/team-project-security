package com.EZALLC.securityapp;

import android.view.View.OnClickListener;
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
import java.util.regex.*;


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
        searchUserInput= findViewById(R.id.searchInput);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(android.view.View v){
                onSearch(searchUserInput.getText().toString());
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
     * @param fileHash
     * @return true if valid file hash and false if not
     */
    public static boolean validFileHash(String fileHash)
    {
        String fileSHA256
                = "^[A-Fa-f0-9]{64}$";
        String fileSHA1
                ="\\b[0-9a-f]{5,40}\\b";
        String fileMD5 ="/^([a-f\\d]{32}|[A-F\\d]{32})$/";
        Pattern SHA256 = Pattern.compile(fileSHA256);
        Matcher m = SHA256.matcher(fileHash);
        Pattern SHA1 = Pattern.compile(fileSHA1);
        Matcher t = SHA1.matcher(fileHash);
        Pattern MD5= Pattern.compile(fileMD5);
        Matcher z = SHA256.matcher(fileHash);
        if(m.matches()||t.matches() ||  z.matches()){
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * This onSearch method receives users file hash or IP address
     * and displays on page
     * @param   IpOrFile
     * @return arraylist with String IP or FileHash and initial input
     */
    public void onSearch(String IpOrFile){
        ipORFile = searchUserInput.getText().toString();
        if(ipORFile.isEmpty()){
            Toast.makeText(Search.this, "Enter a IP address or file Hash", Toast.LENGTH_SHORT).show();
        }
        else{
            if(isValidIPAddress(ipORFile)){
                Toast.makeText(Search.this, "Valid Ip Address "+ipORFile, Toast.LENGTH_SHORT).show();

            }
            else if(validFileHash(ipORFile)){
                Toast.makeText(Search.this, "Valid File Hash "+ipORFile, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(Search.this, "Enter a IP address or file Hash", Toast.LENGTH_SHORT).show();
            }
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