package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class WatchlistDetail extends AppCompatActivity {

    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    private String intentKey = "DATA";
    private TextView display;
    private String COLLECTION;
    private String email;
    private String threatRecieved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Stored Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        display = findViewById(R.id.data_display);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        COLLECTION = email + "'s Threats";

        threatRecieved = getIntent().getStringExtra(intentKey);
        matchThreat();
    }

    public void matchThreat() {
        mDb.collection(COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Threat t = document.toObject(Threat.class);
                            if (t.getId().equals(threatRecieved)) {
                                displayIp(t);
                                return;
                            }
                        }
                        display.setText("Somehow you arrived on this page with data we don't have :/");
                    }
                });
    }

    public void displayIp(Threat t) {
        display.setText(t.getType() + "- " + t.getId()+ "\n" +
                "Regional Internet Registry: " + t.getRegionalInternetRegistry() + "\n" +
                "A.S Owner: " + t.getAsOwner() + "\n" +
                "Continent: " + t.getContinent() + "\n" +
                "Country: " + t.getCountry() + "\n" +
                "Total Harmless Results: " + Integer.toString(t.getHarmless()) + "\n" +
                "Total Malicious Results: " + Integer.toString(t.getMalicious()) + "\n" +
                "Total Suspicious Results: " + Integer.toString(t.getSuspicious()) + "\n" +
                "Total Undetected Results: " + Integer.toString(t.getUndetected()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, WatchList.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}