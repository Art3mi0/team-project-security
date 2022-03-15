package com.EZALLC.securityapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Information_1 extends AppCompatActivity {
    TextView tv;
    Button IncButton;
    Button DecButton;
    private float TextSize = 14f;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv =(TextView) findViewById(R.id.textView6);
        IncButton = (Button) findViewById(R.id.buttonInc);
        DecButton = (Button) findViewById(R.id.buttonDec);
        IncButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextSize += 4f;
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
            }

        });
        DecButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                TextSize -= 4f;
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSize);
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
