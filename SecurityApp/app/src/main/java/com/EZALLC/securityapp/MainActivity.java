package com.EZALLC.securityapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
    private String email;
    private String COLLECTION;
    haveibeenpwndinterface Haveibeenpwndinterface;

    private ArrayList<Threat> mThreats;

    private EditText passwordView;
    private TextView favoritesText;
    private TextView welcomeText;
    EditText enterEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordView = findViewById(R.id.passwordDisplay);
        favoritesText = findViewById(R.id.favorites);
        welcomeText = findViewById(R.id.welcome_text);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        Haveibeenpwndinterface = PulsediveClient.getClient().create(haveibeenpwndinterface.class);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        try {
            email = user.getEmail();
        }
        catch (Exception ex) {
            Log.e(TAG, String.valueOf(ex));
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
        welcomeText.setText("User: " + email);
        COLLECTION = email + "'s Threats";
        getThreats();





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Creates the options menu on start
        // Should only be called once
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.trendingthreats:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent myIntent2 = new Intent(this, TrendingThreats.class);
                startActivity(myIntent2);
                return true;

            case R.id.Search:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent myIntent4 = new Intent(this, Search.class);
                startActivity(myIntent4);
                return true;

            case R.id.Info:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent myIntent5 = new Intent(this, Information.class);
                startActivity(myIntent5);
                return true;
            case R.id.WatchList:
                // User chose the "Watchlist" action, sends to that page
                Intent myIntent6 = new Intent(this, WatchList.class);
                startActivity(myIntent6);
                return true;


            case R.id.sign_out:
                mAuth.signOut();
                FirebaseUser user = mAuth.getCurrentUser();

                Toast.makeText(MainActivity.this, "Log Out Successful!",
                        Toast.LENGTH_SHORT).show();

                Intent myIntent3 = new Intent(this,Login.class );
                startActivity(myIntent3);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    /**
     * this function randomly generates passwords for the user to use
     * param view = button click
     * Output = an error or a new password
     */
    public String GetPassword(int length) {
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();

        Random rand = new Random();

        for(int i = 0; i < length; i++){
            char c = chars[rand.nextInt(chars.length)];
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }



    public void onGeneratePassword(View view) {
        String password;
        password = GetPassword(10);
        passwordView.setText(password);

    }




    /**
     * This method creates an array list of the threat class from the users database, and uses the setFavorites
     * method to set the correct text.
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
                            Log.d(TAG, t.getId());
                            Log.d(TAG, Boolean.toString(t.getIsFavorite()));
                        }
                        if (mThreats != null) {
                            Log.d(TAG, "threats not null");
                            setFavorites(favoritesText);
                        }
                    }
                });
    }

    /**
     * This method pulls the data from the favorited threat and displays it. If a threat is not favorited,
     * a different message will appear.
     * @param textView - The favorites textview already initialized should be inputed
     */
    public void setFavorites(TextView textView) {
        for (int i = 0; i < mThreats.size(); i++) {
            if (mThreats.get(i).getIsFavorite()) {
                if (mThreats.get(i).getType().equals("ip_address")) {
                    textView.setText(mThreats.get(i).getType() + "- " + mThreats.get(i).getId() + "\n" +
                            "Regional Internet Registry: " + mThreats.get(i).getRegionalInternetRegistry() + "\n" +
                            "A.S Owner: " + mThreats.get(i).getAsOwner() + "\n" +
                            "Continent: " + mThreats.get(i).getContinent() + "\n" +
                            "Country: " + mThreats.get(i).getCountry() + "\n" +
                            "Total Harmless Results: " + Integer.toString(mThreats.get(i).getHarmless()) + "\n" +
                            "Total Malicious Results: " + Integer.toString(mThreats.get(i).getMalicious()) + "\n" +
                            "Total Suspicious Results: " + Integer.toString(mThreats.get(i).getSuspicious()) + "\n" +
                            "Total Undetected Results: " + Integer.toString(mThreats.get(i).getUndetected()));
                    return;
                } else if (mThreats.get(i).getType().equals("url")) {
                    textView.setText(mThreats.get(i).getType() + " - " + mThreats.get(i).getId()+ "\n" +
                            "Total Harmless Results: " + Integer.toString(mThreats.get(i).getHarmless()) + "\n" +
                            "Total Malicious Results: " + Integer.toString(mThreats.get(i).getMalicious()) + "\n" +
                            "Total Suspicious Results: " + Integer.toString(mThreats.get(i).getSuspicious()) + "\n" +
                            "Total Undetected Results: " + Integer.toString(mThreats.get(i).getUndetected()));
                    return;
                } else if(mThreats.get(i).getType().equals("email")) {
                    String displayText = mThreats.get(i).getType() + "- " + mThreats.get(i).getId()+ "\n";
                    for (int j = 0; j < mThreats.get(i).getBreaches().size(); j++) {
                        displayText += mThreats.get(i).getBreaches().get(j);
                    }
                    Log.d(TAG, displayText);
                    textView.setText(displayText);
                    return;
                }
            }
        }
        textView.setText("You have not yet set a favorite from your watchlist.");
    }


}