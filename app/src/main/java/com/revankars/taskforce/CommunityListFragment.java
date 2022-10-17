package com.revankars.taskforce;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;


public class CommunityListFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    /*RecyclerView recview2;
    DatabaseReference databaseReference;
    myadapter2 adapter;*/
    SearchView  searchCommunityView;



    Button createCommunity,goToCommunityHelp,joinCommunitybutton;



    private String mParam1;
    private String mParam2;

    public CommunityListFragment() {
        // Required empty public constructor
    }

    public static CommunityListFragment newInstance(String param1, String param2) {
        CommunityListFragment fragment = new CommunityListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_community_list, container, false);


        joinCommunitybutton = view.findViewById(R.id.JOINCommunityButton);


        joinCommunitybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new communityListRecyclerview()).addToBackStack(null).commit();
            }
        });
        goToCommunityHelp = view.findViewById(R.id.goToPublicCommunity);


        /*recview2 = view.findViewById(R.id.recview2);
        searchCommunityView = view.findViewById(R.id.searchcommunityview);*/
        /*searchCommunityView.setQueryHint("Search by Community");*/

        /*searchCommunityView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCommunityView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        FirebaseRecyclerOptions<communitymodel> options =
                                new FirebaseRecyclerOptions.Builder<communitymodel>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Communities").orderByChild("CommunityName").startAt(query).endAt(query +"\uf8ff"), communitymodel.class)
                                        .build();
                        adapter = new myadapter2(options);
                        adapter.startListening();
                        recview2.setAdapter(adapter);
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        FirebaseRecyclerOptions<communitymodel> options =
                                new FirebaseRecyclerOptions.Builder<communitymodel>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Communities").orderByChild("CommunityName").startAt(newText).endAt(newText +"\uf8ff"), communitymodel.class)
                                        .build();
                        adapter= new myadapter2(options);
                        adapter.startListening();
                        recview2.setAdapter(adapter);
                        return false;
                    }
                });
            }
        });*/ // searchfirbase

        goToCommunityHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String communityName = "Public Community";
                Intent intent = new Intent(getActivity(),MainActivity.class);
                intent.putExtra("CommunityName",communityName);
                startActivity(intent);
            }
        });
        createCommunity = view.findViewById(R.id.CreateCommunityButton);
        createCommunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new CreateCommunityFragment()).addToBackStack(null).commit();
            }
        });
        return view;

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);//to reverse the recycler view
        layoutManager.setStackFromEnd(true);//to reverse the recycler view
        recview2.setHasFixedSize(false);
        recview2.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Communities");


        FirebaseRecyclerOptions<communitymodel> options2 =
                new FirebaseRecyclerOptions.Builder<communitymodel>()
                        .setQuery(databaseReference, communitymodel.class)
                        .build();

        adapter = new myadapter2(options2);
        recview2.setAdapter(adapter);*///firebase recycler view





    /*}
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }*/
}}