package com.revankars.taskforce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class CommunityActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community2);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new CommunityListFragment()).commit();
    }


}