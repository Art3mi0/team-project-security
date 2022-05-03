package com.EZALLC.securityapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.*;


public class Search extends AppCompatActivity {
    private static final String TAG = "search";

    private static long mLastClickTime;
    private Button searchButton;
    private EditText searchUserInput;
    private String SearchText;
    private ScrollView searchView;
    private ListView ListViewSearch;
    public ArrayAdapter<Recent> adapter;
    private ArrayList<Recent> SearchArray;
    private TextView urlText;

    private final FirebaseFirestore mDb = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    private String COLLECTION;
    private String email;
    private String type;
    private int number = 0;
    private int old;
    private String docId;

    ArrayList<String> bundle= new ArrayList<>();



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
        urlText = findViewById(R.id.description);
        urlText.setMovementMethod(LinkMovementMethod.getInstance());

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        email = user.getEmail();
        COLLECTION = email + "'s Recents";

        adapter = new SearchAdapter(this, new ArrayList<Recent>());

        Intent PageIPHashing = new Intent(Search.this,IPHashInfo.class);
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new android.view.View.OnClickListener(){
            public void onClick(android.view.View v){
                if (searchUserInput.getText().toString().isEmpty()) {
                    Toast.makeText(Search.this, "Enter a valid email address, IP address, or URL", Toast.LENGTH_SHORT).show();
                    searchButton.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            searchButton.setEnabled(true);
                            Log.d(TAG, "resend1");

                        }
                    }, 2000);
                }
                else if (isValidIPAddress(searchUserInput.getText().toString().trim())==false && validURl(searchUserInput.getText().toString().trim())==false && !isValid(searchUserInput.getText().toString().trim())) {
                    Toast.makeText(Search.this, "Enter a valid email address, IP address, or URL", Toast.LENGTH_SHORT).show();
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
                    if(isValidIPAddress(searchUserInput.getText().toString().trim())==true){
                        bundle.clear();
                        //String[] vaildIP = {searchUserInput.getText().toString(), "IP"};
                        bundle.add(searchUserInput.getText().toString().trim());
                        bundle.add("IP");
                        //intent.putExtra("EXTRA_BUNDLE",vaildIP);
                        intent.putExtra("key",bundle);
                        startActivity(intent);
                    }
                    else if(validURl(searchUserInput.getText().toString().trim())==true){
                        bundle.clear();
                        //String[] URL = {searchUserInput.getText().toString(), "URL"};
                        bundle.add(searchUserInput.getText().toString().trim());
                        bundle.add("URL");
                        //intent.putExtra("EXTRA_BUNDLE",URL);
                        intent.putExtra("key",bundle);
                        startActivity(intent);
                    }else if(isValid(searchUserInput.getText().toString().trim())){
                        bundle.clear();
                        //String[] URL = {searchUserInput.getText().toString(), "URL"};
                        bundle.add(searchUserInput.getText().toString());
                        bundle.add("EMAIL");
                        //intent.putExtra("EXTRA_BUNDLE",URL);
                        intent.putExtra("key",bundle);
                        startActivity(intent);

                    }
                    //Search.this.startActivity(PageIPHashing);

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
        ListViewSearch.setAdapter(adapter);
        getRecents();
        ListViewSearch.setVisibility(android.view.View.VISIBLE);
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    // @Override
                    public void onItemClick(AdapterView<?> listView, View view, int i, long l) {
                        String item = SearchArray.get(i).getSearch();
                        Log.d(TAG, item);
                        searchUserInput.setText(item);
                    }
                };
        ListViewSearch.setOnItemClickListener(itemClickListener);
    }
    /**
     * This isValidIPAddress method receives users file hash or IP address
     * and check if valid ip address
     * @param
     * @return true if ip address and false if not
     */

    /**
     * This isValidIPAddress method receives users file hash or IP address
     * and check if valid ip address in form IPv4
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
     * and check if valid URL in for .com,.net,.gov
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
        number++;
        for (int i = 0; i < SearchArray.size(); i ++) {
            if (SearchArray.get(i).getSearch().equals(ipOrUrlAdd)) {
                old = SearchArray.get(i).getNumber();
                removeOld(false);
                SearchArray.remove(i);
                break;
            }
        }
        if(SearchArray.size() == 10) {
            removeOld(true);
        }
        Recent newRecent = new Recent(type, ipOrUrlAdd, number);

        mDb.collection(COLLECTION)
                .add(newRecent)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Recent added successfully.");
                        getRecents();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Could not add recent!");
                    }
                });
        //ListViewSearch.setAdapter(SearchAdapter);
    }

    public void removeOld(Boolean actualOld) {
        if (actualOld) {
            old = SearchArray.get(SearchArray.size() - 1).getNumber();
            SearchArray.remove(SearchArray.size() - 1);
        }
        mDb.collection(COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Recent r = document.toObject(Recent.class);
                            if (r.getNumber() == old) {
                                docId = document.getId();
                                break;
                            }
                        }
                        mDb.collection(COLLECTION).document(docId).delete();
                    }
                });
    }
    /**
     * This onSearch method receives users email address,URL or IP address
     * and displays on page
     * @param   ipOrURLOrEmail
     */
    public void onSearch(String ipOrURLOrEmail){
            if(isValidIPAddress(ipOrURLOrEmail)){
                type = "IP";
                add_clear_list(ipOrURLOrEmail);
                Toast.makeText(Search.this, "Valid Ip Address "+ipOrURLOrEmail+"\nSearching", Toast.LENGTH_SHORT).show();

            }
            else if(validURl(ipOrURLOrEmail)){
                type = "URL";
                add_clear_list(ipOrURLOrEmail);
                Toast.makeText(Search.this, "Valid URL "+ipOrURLOrEmail+"\nSearching", Toast.LENGTH_SHORT).show();
            }
            else if(isValid(ipOrURLOrEmail)==true){
                type = "EMAIL";
                add_clear_list(ipOrURLOrEmail);
                Toast.makeText(Search.this, "Valid Email Address "+ipOrURLOrEmail+"\nSearching", Toast.LENGTH_SHORT).show();
            }

            else {
                Toast.makeText(Search.this, "Enter a valid email address, IP address, or URL", Toast.LENGTH_SHORT).show();
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

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    class SearchAdapter extends ArrayAdapter<Recent> {
        ArrayList<Recent> recentsList;
        SearchAdapter(Context context, ArrayList<Recent> recentsList) {
            super(context, 0, recentsList);
            this.recentsList = recentsList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_item, parent, false);
            }

            TextView searchType = convertView.findViewById(R.id.searchType);
            TextView searchThing = convertView.findViewById(R.id.searchThing);

            Recent r = recentsList.get(position);
            searchType.setText(r.getType());
            searchThing.setText(r.getSearch());

            return convertView;
        }
    }

    public void getRecents() {
        mDb.collection(COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        SearchArray = new ArrayList<>();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Recent r = document.toObject(Recent.class);
                            if (r.getNumber() > number) {
                                number = r.getNumber();
                            }
                            SearchArray.add(r);
                            Log.d(TAG, r.getSearch());
                        }
                        adapter.clear();
                        try {
                            SearchArray.get(0);
                            Collections.sort(SearchArray, new Sortbynumber());
                            Log.d(TAG, "The last item is " + SearchArray.get(SearchArray.size()-1).getSearch());
                            adapter.addAll(SearchArray);
                        } catch(Exception e) {
                            number = 0;
                        }
                    }
                });
    }

}