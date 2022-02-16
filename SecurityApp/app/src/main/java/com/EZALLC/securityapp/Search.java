package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Search extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //done immediately
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TextView Component = (TextView) findViewById(R.id.Search_Componet);
        Component.setText(R.string.SearchHello);


    }

}