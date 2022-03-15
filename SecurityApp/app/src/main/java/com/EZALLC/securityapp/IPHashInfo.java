package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPHashInfo extends AppCompatActivity {

    TextView fTextView;

    PulsediveAPIInterface pulsediveAPIInterface;

    Integer the_qid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphash_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        pulsediveAPIInterface = PulsediveClient.getClient().create(PulsediveAPIInterface.class);
        fTextView = findViewById(R.id.IP_info_box);

        TextView title = (TextView) findViewById(R.id.demo_title);
        title.setText(R.string.iphashdemo);

        getTodoUsingRouteParameter();
        (new Handler()).postDelayed(this::getUser, 15000);

    }
    /**
     * this method will call the call the API PulseDive for information on the IP recieved from Search Activity
     * @param view = OnCreate will call this methods with an IP
     * Output = Generate relevant information on the IP to be then displayed in OnCreate
     */
    public void onCallPulseDive(View view) {
        return;

    }

    /**
     * this method will call the call the API VirusTotal for information on the Hash recieved from Search Activity
     * @param view = OnCreate will call this methods with a Hash
     * Output = Generate relevant information on the Hash to be then displayed in OnCreate
     */
    public void onCallVirusTotal(View view) {
        return;

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


    public void getTodoUsingRouteParameter() {
        Call<Todo> todoCall = pulsediveAPIInterface.getTodo("2600:1004:b032:ae2d:482e:bdc2:65b9:76","1");
        todoCall.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                //Log.e(TAG, "onResponse: " + response.body() );
                the_qid = response.body().getQid();
                Toast.makeText(IPHashInfo.this,
                        "IP was Put Into Queue, info will display shortly. Please be patient.", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Todo> call, Throwable t) {
                //Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }

    public void getUser(){
        //Execute the Network request
        String da_qid = String.valueOf(the_qid);
        Call<Post> call = pulsediveAPIInterface.getPost(da_qid,"1");
        //Execute the request in a background thread
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()){
                    fTextView.setText("It gets this far that's it.");
                    return;
                }

//                if (response.body() != null){
//                    //Get the values
                String userContent = "";
                userContent += "Success: " + response.body().getSuccess() + "\n";
                userContent += "Q ID: " + response.body().getQid() + "\n";
                userContent += "Status: " + response.body().getStatus() + "\n";
                userContent += "Risk: " + response.body().getData().getRisk() + "\n";
                userContent += "Longitude: " + response.body().getData().getProperties().getGeo().getLong() + "\n";
                userContent += "Latitude: " + response.body().getData().getProperties().getGeo().getLat() + "\n";
                userContent += "Region: " + response.body().getData().getProperties().getGeo().getRegion() + "\n";
                userContent += "Address: " + response.body().getData().getProperties().getGeo().getAddress() + "\n";
                userContent += "ISP: " + response.body().getData().getProperties().getGeo().getIsp() + "\n";
                userContent += "City: " + response.body().getData().getProperties().getGeo().getCity() + "\n";
                userContent += "ASN: " + response.body().getData().getProperties().getGeo().getAsn() + "\n";
                userContent += "Country: " + response.body().getData().getProperties().getGeo().getCountry() + "\n";
                userContent += "CountyCode: " + response.body().getData().getProperties().getGeo().getCountrycode() + "\n";
                //userContent += "Organization: " + response.body().getData().getProperties().getGeo().getOrg() + "\n";
                //userContent += "Organization: " + response.body().getData().getProperties().getWhois() + "\n";

                fTextView.setText(userContent);
//                }
                //Log.e(TAG, "onResponse: " + response.body() );
            }
            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                fTextView.setText("Failure: " + t);
            }
        });
    }


}
