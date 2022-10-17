package com.revankars.taskforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;

public class MainActivity extends AppCompatActivity {

    private Button getHelp;
    RecyclerView recview;
    myadapter adapter;
    SearchView searchView;
    TextView textView;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        getHelp = findViewById(R.id.getHelpButton);
        recview= (RecyclerView)findViewById(R.id.recview);
        textView = findViewById(R.id.textView);
        searchView = findViewById(R.id.searchview);


        //code for populating recycler view
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);//to reverse the recycler view
        layoutManager.setStackFromEnd(true);//to reverse the recycler view
        recview.setLayoutManager(layoutManager);



        Intent intent = getIntent();
        String CommunityNameName = intent.getStringExtra("CommunityName");


        textView.setText(CommunityNameName);

        databaseReference = FirebaseDatabase.getInstance().getReference("Communities").child(CommunityNameName).child(CommunityNameName);
        Query query = databaseReference.orderByKey();

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(query, model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);

        getHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,HelpActivity.class);
                startActivity(i);

                Intent intent = new Intent(MainActivity.this,HelpActivity.class);
                intent.putExtra("CommunityName",CommunityNameName);
                startActivity(intent);}});

    //code for search by name
        searchView.setQueryHint("Search by Name");
      searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        FirebaseRecyclerOptions<model> options =
                                new FirebaseRecyclerOptions.Builder<model>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference("Communities").child(CommunityNameName).child(CommunityNameName).orderByChild("Name").startAt(query).endAt(query +"\uf8ff"), model.class)
                                        .build();
                        adapter = new myadapter(options);
                        adapter.startListening();
                        recview.setAdapter(adapter);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        FirebaseRecyclerOptions<model> options =
                                new FirebaseRecyclerOptions.Builder<model>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference("Communities").child(CommunityNameName).child(CommunityNameName).orderByChild("Name").startAt(newText).endAt(newText +"\uf8ff"), model.class)
                                        .build();
                        adapter = new myadapter(options);
                        adapter.startListening();
                        recview.setAdapter(adapter);
                        return false;
                    }
                });
            }

        });



    }



    public void onBackPressed(){
        Intent intent = new Intent(MainActivity.this,CommunityActivity.class);

        startActivity(intent);
    }
    //code for realtime data change
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}