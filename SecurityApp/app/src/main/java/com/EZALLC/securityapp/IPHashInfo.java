package com.EZALLC.securityapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPHashInfo extends AppCompatActivity {

    TextView fTextView;

    VirusTotalAPI virusTotalAPI;

    TextView url_info_box;

    /*Will get the ip from an intent sent by Search activity.
    * Then the code will run to display info about it
    * Should be smooth and seamless.*/
    //Integer the_qid;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphash_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        virusTotalAPI = VirusTotalClient.getClient().create(VirusTotalAPI.class);

        fTextView = findViewById(R.id.IP_info_box);

        TextView title = (TextView) findViewById(R.id.demo_title);
        title.setText(R.string.iphashdemo);

//        String encodedURL = Base64.getUrlEncoder().encodeToString("https://tinder.com/".getBytes(StandardCharsets.UTF_8));
//        encodedURL = encodedURL.replace("==","");
        url_info_box = (TextView) findViewById(R.id.hash_box);
//        hash.setText(encodedURL);

        getUser();
        getURLHash();
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

    public void getUser(){
        //Execute the Network request
        String da_qid = "174.216.16.12";
        Call<IpInfo> call = virusTotalAPI.getIPInfo(da_qid);
        //Execute the request in a background thread
        call.enqueue(new Callback<IpInfo>() {
            @Override
            public void onResponse(Call<IpInfo> call, Response<IpInfo> response) {
                if (!response.isSuccessful()){
                    fTextView.setText("It gets this far that's it.");
                    return;
                }

//                if (response.body() != null){
//                    //Get the values
                //String res = response.body().string();
                String userContent = "";
                //userContent += "Success: " + response.body().toString() + "\n";
                userContent += "Success: " + response.body().getData().getAttributes().getAsn()+ "\n";
                userContent += "Harmless: " + response.body().getData().getAttributes().getLastAnalysisStats().getHarmless()+ "\n";
                userContent += "Malicious: " + response.body().getData().getAttributes().getLastAnalysisStats().getMalicious()+ "\n";
                userContent += "Suspicious: " + response.body().getData().getAttributes().getLastAnalysisStats().getSuspicious()+ "\n";
                userContent += "Undetected: " + response.body().getData().getAttributes().getLastAnalysisStats().getUndetected()+ "\n";
//                userContent += "Timeout: " + response.body().getData().getAttributes().getLastAnalysisStats().getTimeout()+ "\n";

                fTextView.setText(userContent);
//                }
                //Log.e(TAG, "onResponse: " + response.body() );
            }
            @Override
            public void onFailure(Call<IpInfo> call, Throwable t) {
                fTextView.setText("Failure: " + t);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getURLHash(){

        String encodedURL = Base64.getUrlEncoder().encodeToString("https://tinder.com/".getBytes(StandardCharsets.UTF_8));
        encodedURL = encodedURL.replace("==","");

        Call<HashInfo> call = virusTotalAPI.getHashInfo(encodedURL);
        //Execute the request in a background thread
        call.enqueue(new Callback<HashInfo>() {
            @Override
            public void onResponse(Call<HashInfo> call, Response<HashInfo> response) {
                if (!response.isSuccessful()){
                    fTextView.setText("It gets this far that's it.");
                    return;
                }

//                if (response.body() != null){
//                    //Get the values
                //String res = response.body().string();
                String userContent = "";
                //userContent += "Success: " + response.body().toString() + "\n";
                userContent += "Success: " + response.body().getData().getAttributes().getTitle()+ "\n";
                userContent += "Harmless: " + response.body().getData().getAttributes().getTotalVotes().getHarmless()+ "\n";
                userContent += "Malicious: " + response.body().getData().getAttributes().getTotalVotes().getMalicious()+ "\n";

                url_info_box.setText(userContent);
//                }
                //Log.e(TAG, "onResponse: " + response.body() );
            }
            @Override
            public void onFailure(Call<HashInfo> call, Throwable t) {
                fTextView.setText("Failure: " + t);
            }
        });

    }

}
