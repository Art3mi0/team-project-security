package com.EZALLC.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.security.AuthProvider;

public class AutoInfo extends AppCompatActivity {
    TextView tv;
    Button IncButton;
    Button DecButton;
    private float TextSize = 14f;
    private float maxTextSize = 30f;
    private float minTextSize = 10f;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autoinfo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv =(TextView) findViewById(R.id.AutoInfoBox);
        IncButton = (Button) findViewById(R.id.buttonInc5);
        DecButton = (Button) findViewById(R.id.buttonDec5);
        IncButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (TextSize<=maxTextSize){
                    TextSize += 4f;
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
                }
                else{
                    Toast.makeText(AutoInfo.this, "Maximum text size reached", Toast.LENGTH_SHORT).show();
                }
            }

        });
        DecButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (minTextSize<=TextSize){
                    TextSize -= 4f;
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
                }
                else{
                    Toast.makeText(AutoInfo.this, "Minimum text size reached", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, Information.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}