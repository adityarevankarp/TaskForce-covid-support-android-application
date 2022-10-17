package com.revankars.taskforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WalkthroughActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);


        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper2,new walkthroughinfo1()).commit();

    }
}