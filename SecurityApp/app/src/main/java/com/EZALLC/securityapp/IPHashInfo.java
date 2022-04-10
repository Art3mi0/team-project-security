package com.EZALLC.securityapp;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPHashInfo extends AppCompatActivity {
    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private static final String TAG = "IPHASH INFO";


    TextView fTextView;

    VirusTotalAPI virusTotalAPI;

    TextView url_info_box;

    private String email;
    private String COLLECTION;

    private int Harmless;
    private int Success;
    private int Malicious;
    private int Suspicious;
    private int Undetected;
    private String Country;
    private String ASNOwner;
    private String Continent;
    private String Id;
    private String Type;
    private String RegionalInternetRegistry;
    private Boolean IsFavorite;




    /*Will get the ip from an intent sent by Search activity.
     * Then the code will run to display info about it
     * Should be smooth and seamless.*/

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

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        COLLECTION = email + "'s Threats";

        virusTotalAPI = VirusTotalClient.getClient().create(VirusTotalAPI.class);
        fTextView = findViewById(R.id.IP_info_box);
        url_info_box = (TextView) findViewById(R.id.hash_box);

        TextView title = (TextView) findViewById(R.id.demo_title);
        title.setText(R.string.iphashdemo);

        getUser();
        getURLHash();

    }


    public void Add_To_Firebase(View view){

        String test;
        //Toast.makeText(IPHashInfo.this, test = Integer.toString(Harmless),Toast.LENGTH_LONG).show();

        Threat newThreat = new Threat(Type,Id,RegionalInternetRegistry,ASNOwner,Continent,Country,Harmless,Malicious,Suspicious,Undetected, false);

        mDb.collection(COLLECTION)
                .add(newThreat)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Threat added successfully.");
                        Toast.makeText(IPHashInfo.this,
                                "Threat added!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Could not add threat!");
                        Toast.makeText(IPHashInfo.this,
                                "Failed to add threat!",
                                Toast.LENGTH_SHORT).show();
                    }
                });

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
//                    //Handle error here?

                String userContent = "";
                //userContent += "Success: " + response.body().getData().getAttributes().getAsn()+ "\n";

                userContent += "Harmless: " + response.body().getData().getAttributes().getLastAnalysisStats().getHarmless()+ "\n";
                Harmless = response.body().getData().getAttributes().getLastAnalysisStats().getHarmless();

                userContent += "Malicious: " + response.body().getData().getAttributes().getLastAnalysisStats().getMalicious()+ "\n";
                Malicious = response.body().getData().getAttributes().getLastAnalysisStats().getMalicious();

                userContent += "Suspicious: " + response.body().getData().getAttributes().getLastAnalysisStats().getSuspicious()+ "\n";
                Suspicious = response.body().getData().getAttributes().getLastAnalysisStats().getSuspicious();

                userContent += "Undetected: " + response.body().getData().getAttributes().getLastAnalysisStats().getUndetected()+ "\n";
                Undetected = response.body().getData().getAttributes().getLastAnalysisStats().getUndetected();

                userContent += "Country: " + response.body().getData().getAttributes().getCountry()+ "\n";
                Country = response.body().getData().getAttributes().getCountry();

                userContent += "ASOwner: " + response.body().getData().getAttributes().getAsOwner()+ "\n";
                ASNOwner = response.body().getData().getAttributes().getAsOwner();

                //userContent += "Undetected: " + response.body().getData().getAttributes().getLastAnalysisStats().getUndetected()+ "\n";

                userContent += "Regional Internet Registry: " + response.body().getData().getAttributes().getRegionalInternetRegistry()+ "\n";
                RegionalInternetRegistry = response.body().getData().getAttributes().getRegionalInternetRegistry();

                userContent += "Continent: " + response.body().getData().getAttributes().getContinent()+ "\n";
                Continent = response.body().getData().getAttributes().getContinent();

                userContent += "Id: " + response.body().getData().getId()+ "\n";
                Id = response.body().getData().getId();

                userContent += "Type: " + response.body().getData().getType()+ "\n";
                Type = response.body().getData().getType();

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
//                    //Handle API errors here?

                String userContent = "";
                userContent += "Success: " + response.body().getData().getAttributes().getTitle()+ "\n";
                //userContent += "Harmless: " + response.body().getData().getAttributes().getTotalVotes().getHarmless()+ "\n";
                //userContent += "Malicious: " + response.body().getData().getAttributes().getTotalVotes().getMalicious()+ "\n";

                url_info_box.setText(userContent);

            }
            @Override
            public void onFailure(Call<HashInfo> call, Throwable t) {
                fTextView.setText("Failure: " + t);
            }
        });
    }
}
