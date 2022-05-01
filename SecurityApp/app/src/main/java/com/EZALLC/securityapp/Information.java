package com.EZALLC.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Information extends AppCompatActivity {
    private ListView ListViewinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //done immediately
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Information");
        ListViewinfo = findViewById(R.id.ListViewInfo);
        String[] InfoHolder = new String[] {
                "Ip Address",
                "Test two",
                "Test three",
                "  ",
                "  ",
                "  "
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item, InfoHolder);
        ListViewinfo.setAdapter(adapter);
        ListViewinfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent pageInfoIP = new Intent(Information.this,IpAddressInfo.class);
                       Information.this.startActivity(pageInfoIP);
                        break;
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
