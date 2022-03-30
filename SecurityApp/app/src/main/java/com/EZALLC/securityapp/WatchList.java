package com.EZALLC.securityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatchList extends AppCompatActivity {

    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private static final String TAG = "Watchlist";

    private ListView watchList;
    private EditText userInput;
    private String COLLECTION;
    private String docId;
    private String email;
    private String threat;
    private ArrayList<Threat> mThreats;
    private ArrayAdapter<Threat> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("WatchList");

        watchList = findViewById(R.id.the_watchlist);
        userInput = findViewById(R.id.watchlist_input);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        COLLECTION = email + "'s Threats";
        adapter = new ThreatAdapter(this, new ArrayList<Threat>());
        watchList.setAdapter(adapter);
        getThreats();

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                   // @Override
                    public void onItemClick(AdapterView<?> listView, View view, int i, long l) {
                        String item = mThreats.get(i).getThreat();
                        Log.d(TAG, item);
                        userInput.setText(item);
                    }
                };
        watchList.setOnItemClickListener(itemClickListener);
    }

    /**
     * This method allows the user to add an appropriately entered ip or file hash from an edit text
     * view to the user's database, ensure duplicates don't appear, and refreshes the user's list in
     * real-time.
     * @param view - When the button is clicked
     * Output - Adds an IP or file hash to the database and refreshes the list view
     */
    public void onAddToList(View view) {
        threat = userInput.getText().toString();
        if (threat.isEmpty()) {
            Toast.makeText(WatchList.this, "Can't add nothing", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < mThreats.size(); i++) {
            Log.d(TAG, "Checking if " + threat + " = " + mThreats.get(i));
            if (threat.equals(mThreats.get(i).getThreat())) {
                Toast.makeText(WatchList.this, "Already in list", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Threat newThreat = new Threat(threat);
        mThreats.add(newThreat);
        Log.d(TAG, "Submitted threat: " + newThreat.getThreat());
        mDb.collection(COLLECTION)
                .add(newThreat)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Threat added successfully.");
                        Toast.makeText(WatchList.this,
                                "Threat added!",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Could not add threat!");
                        Toast.makeText(WatchList.this,
                                "Failed to add threat!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        adapter.clear();
        adapter.addAll(mThreats);
        return;
    }

    /**
     * This method removes an item from the list by checking if the input is empty, then comparing the
     * item with every item in the current list. If a match is found, the item is deleted, and
     * the list is updated in real time.
     * @param view - The act of clicking the button
     */
    public void onRemoveFromList(View view) {
        threat = userInput.getText().toString();
        if (threat.isEmpty()) {
            Toast.makeText(WatchList.this, "Can't remove nothing", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < mThreats.size(); i++) {
            Log.d(TAG, "Checking if " + threat + " = " + mThreats.get(i));
            if (threat.equals(mThreats.get(i).getThreat())) {
                mDb.collection(COLLECTION)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                                    Threat t = document.toObject(Threat.class);
                                    if (t.getThreat().equals(threat)) {
                                        docId = document.getId();
                                        break;
                                    }
                                }
                                mDb.collection(COLLECTION).document(docId).delete();
                                getThreats();
                                Toast.makeText(getApplicationContext(),"Deleted " + threat + " from list", Toast.LENGTH_LONG).show();
                            }
                        });
                Log.d(TAG, "Adapter should have updated");
                return;
            }
        }

        Toast.makeText(WatchList.this, "Already not in list", Toast.LENGTH_SHORT).show();
        return;
    }

    /**
     * This method creates an array list of the threat class from the users database, and sets the
     * adapter to the list. When the adapter changes, it changes in real time, while only changing
     * the list does nothing to affect what the user sees.
     */
    public void getThreats() {
        mDb.collection(COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        mThreats = new ArrayList<>();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Threat t = document.toObject(Threat.class);
                            mThreats.add(t);
                            Log.d(TAG, t.getThreat());
                        }
                        adapter.clear();
                        if (mThreats != null) {
                            Log.d(TAG, mThreats.get(0).getThreat());
                            adapter.addAll(mThreats);
                        }
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

    /**
     * This class is required to create the threat list. It creates each item for the list in the view,
     * and without it, it would display the document ids instead of the data in the fields. Uses the
     * watch_list_item xml file for each list item.
     */
    class ThreatAdapter extends ArrayAdapter<Threat> {
        ArrayList<Threat> threatsList;
        ThreatAdapter(Context context, ArrayList<Threat> threatsList) {
            super(context, 0, threatsList);
            this.threatsList = threatsList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.watch_list_item, parent, false);
            }

            TextView threatName = convertView.findViewById(R.id.threatName);

            Threat t = threatsList.get(position);
            threatName.setText(t.getThreat());

            return convertView;
        }
    }
}