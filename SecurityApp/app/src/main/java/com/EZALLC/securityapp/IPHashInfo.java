package com.EZALLC.securityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class IPHashInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iphash_info);

        TextView title = (TextView) findViewById(R.id.demo_title);
        title.setText(R.string.iphashdemo);
    }


}
