package com.EZALLC.securityapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.HtmlCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spanned;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPHashInfo extends AppCompatActivity {
    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private static final String TAG = "IPHASH INFO";

    haveibeenpwndinterface Haveibeenpwndinterface;
    TextView fTextView;

    VirusTotalAPI virusTotalAPI;

    TextView url_info_box;

    private Button da_button;

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
    private ProgressBar spinner;

    private Boolean flag = false;
    private String docId;

    String url;
    String ip;

    String[] myStrings;





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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Data from APIs");

        spinner=(ProgressBar)findViewById(R.id.pBar);
        spinner.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        COLLECTION = email + "'s Threats";

        virusTotalAPI = VirusTotalClient.getClient().create(VirusTotalAPI.class);
        fTextView = findViewById(R.id.IP_info_box);


        TextView title = (TextView) findViewById(R.id.demo_title);

        da_button = (Button)findViewById(R.id.button);
        da_button.setEnabled(false);

        ArrayList<String> list = (ArrayList<String>) getIntent().getSerializableExtra("key");
        Haveibeenpwndinterface = PulsediveClient.getClient().create(haveibeenpwndinterface.class);
        title.setText("INFO FOR \n"+ list.get(0));
        Log.w(TAG, list.toString());
        Log.w(TAG, "Should be above this!!!");

        if(list.get(1).equals("IP")) {
            getUser(list.get(0));
        }else if(list.get(1).equals("EMAIL")){
            da_button.setVisibility(View.GONE);
            onCheckEmail(list.get(0));
        }else if(list.get(1).equals("URL")){
            getURLHash(list.get(0));
        }
    }


    public void matchThreat(Threat check) {
        mDb.collection(COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Threat t = document.toObject(Threat.class);
                            if (t.getId().equals(check.getId())) {
                                docId = document.getId();
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            mDb.collection(COLLECTION).document(docId).delete();
                        }
                    }
                });
    }

    public void Add_To_Firebase(View view){

        String test;
        //Toast.makeText(IPHashInfo.this, test = Integer.toString(Harmless),Toast.LENGTH_LONG).show();

        Threat newThreat = new Threat(Type,Id,RegionalInternetRegistry,ASNOwner,Continent,Country,Harmless,Malicious,Suspicious,Undetected, false);

        matchThreat(newThreat);

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
                Intent intent = new Intent(this, Search.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getUser(String ip){
        //Execute the Network request
        Call<IpInfo> call = virusTotalAPI.getIPInfo(ip);
        //Execute the request in a background thread
        call.enqueue(new Callback<IpInfo>() {
            @Override
            public void onResponse(Call<IpInfo> call, Response<IpInfo> response) {
                spinner.setVisibility(View.GONE);

                if(response.code() ==400){
                    Toast.makeText(IPHashInfo.this,
                            "Bad request. IP not valid. Pls double check.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(response.code() >= 500){
                    Toast.makeText(IPHashInfo.this,
                            "Server side issue pls try again.",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                String userContent = "";

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

                userContent += "Regional Internet Registry: " + response.body().getData().getAttributes().getRegionalInternetRegistry()+ "\n";
                RegionalInternetRegistry = response.body().getData().getAttributes().getRegionalInternetRegistry();

                userContent += "Continent: " + response.body().getData().getAttributes().getContinent()+ "\n";
                Continent = response.body().getData().getAttributes().getContinent();

                userContent += "Id: " + response.body().getData().getId()+ "\n";
                Id = response.body().getData().getId();

                userContent += "Type: " + response.body().getData().getType()+ "\n";
                Type = response.body().getData().getType();

                fTextView.setText(userContent);

                da_button.setEnabled(true);

            }
            @Override
            public void onFailure(Call<IpInfo> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                //fTextView.setText("Failure: " + t);
                Toast.makeText(IPHashInfo.this,
                        "Check internet connection.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getURLHash(String url){
        //String encodedURL = Base64.getUrlEncoder().encodeToString("https://tinder.com/".getBytes(StandardCharsets.UTF_8));
        String encodedURL = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        encodedURL = encodedURL.replace("==","");

        Call<HashInfo> call = virusTotalAPI.getHashInfo(encodedURL);
        //Execute the request in a background thread
        call.enqueue(new Callback<HashInfo>() {
            @Override
            public void onResponse(Call<HashInfo> call, Response<HashInfo> response) {
                spinner.setVisibility(View.GONE);

                if(response.code() ==400){
                    Toast.makeText(IPHashInfo.this,
                            "Bad request. URL not valid. Pls double check.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(response.code() >= 500){
                    Toast.makeText(IPHashInfo.this,
                            "Server side issue pls try again.",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                if(response.body() == null){
                    Toast.makeText(IPHashInfo.this,
                            "Getting Nothing",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                String userContent = "";
                userContent += "Success: " + response.body().getData().getAttributes().getTitle()+ "\n";
                //userContent += "Harmless: " + response.body().getData().getAttributes().getTotalVotes().getHarmless()+ "\n";
                //userContent += "Malicious: " + response.body().getData().getAttributes().getTotalVotes().getMalicious()+ "\n";

                fTextView.setText(userContent);

            }
            @Override
            public void onFailure(Call<HashInfo> call, Throwable t) {
                spinner.setVisibility(View.GONE);
                Toast.makeText(IPHashInfo.this,
                        "Check internet connection.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * this function uses an API to check if an email has been compromised or not
     * @param  = user input and button click
     * Output = a pop up that says error or email compromised or not
     */
    public void onCheckEmail(String email) {
        //Execute the Network request


        Call<List<Pwned>> call = Haveibeenpwndinterface.getPwned(email, false);
        // if value is empty, return a comment
        // if value is not valid email, return a comment (toast)
        //Execute the request in a background thread
        call.enqueue(new Callback<List<Pwned>>() {
            @Override
            public void onResponse(Call<List<Pwned>> call, Response<List<Pwned>> response) {
                String userContent = "";
//                if (response.code() == 404) {
//                    spinner.setVisibility(View.GONE);
//                    userContent += "This account probably don't exist.";
//                    fTextView.setText(userContent);
//                    return;
//                }
                if (response.body() == null) {
                    spinner.setVisibility(View.GONE);

                    fTextView.setText(R.string.Breaches);
//                    Toast.makeText(IPHashInfo.this,
//                            "No Breaches",
//                            Toast.LENGTH_LONG).show();

                    Log.e(TAG, "onResponse: " + response.body());
                    return;
                }
                //loop incremented value
                if (response.code() == 404) {
                    spinner.setVisibility(View.GONE);
                    userContent += "This account probably don't exist.";

                }
                for (int i = 0; i < response.body().size(); i++) {
                    spinner.setVisibility(View.GONE);
                    userContent += "Name: " + response.body().get(i).getName() + "\n";
                    userContent += "Domain: " + response.body().get(i).getDomain() + "\n";
                    userContent += "Title: " + response.body().get(i).getTitle() + "\n";
                    userContent += "Breach Date: " + response.body().get(i).getBreachDate() + "\n";
                    Spanned spanned = HtmlCompat.fromHtml(response.body().get(0).getDescription(), HtmlCompat.FROM_HTML_MODE_COMPACT);
                    userContent += spanned+"\n";
                    userContent+=""+"\n";
                    //userContent += "Description: " + response.body().get(i).getDescription() + "\n";


                }
//                Toast.makeText(IPHashInfo.this,
//                        userContent,
//                        Toast.LENGTH_LONG).show();

                fTextView.setText(userContent);

                Log.e(TAG, "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<List<Pwned>> call, Throwable t) {
                Log.e(TAG, "onResponse: " + "It just gets here.");
            }
        });
    }
}
