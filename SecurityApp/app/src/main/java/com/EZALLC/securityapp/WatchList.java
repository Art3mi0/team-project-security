package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.view.MenuItem;
import android.view.View;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatchList extends AppCompatActivity {

    private ListView watchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("WatchList");

        watchList = findViewById(R.id.the_watchlist);
        String[] placeHolder = new String[] {
                "This is an IP",
                "This is a hash file",
                "This is filler",
                "This is possibly the list item needed to enable list scrolling",
                "But just to make sure",
                "I will add another",
                "Maybe one more",
                "But thats it",
                "..."
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, placeHolder);
        watchList.setAdapter(adapter);

    }

    /**
     * This method allows the user to add an appropriately entered ip or file hash from an edit text
     * view to the user's database, ensure duplicates don't appear, and refreshes the user's list in
     * real-time.
     * @param view - When the button is clicked
     * Output - Adds an IP or file hash to the database and refreshes the list view
     */
    public void onAddToList(View view) {
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
}