package com.revankars.taskforce;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class communityListRecyclerview extends Fragment {

    SearchView SearchViewForCommunityList;
    RecyclerView a;
    myadapter2 adapter;
    DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public communityListRecyclerview() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static communityListRecyclerview newInstance(String param1, String param2) {
        communityListRecyclerview fragment = new communityListRecyclerview();
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
        View view = inflater.inflate(R.layout.fragment_community_list_recyclerview, container, false);


        SearchViewForCommunityList = view.findViewById(R.id.searchViewCommunity);
        SearchViewForCommunityList.setQueryHint("Search by Community");


        SearchViewForCommunityList.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchViewForCommunityList.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        FirebaseRecyclerOptions<communitymodel> options =
                                new FirebaseRecyclerOptions.Builder<communitymodel>()
                                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Communities").orderByChild("CommunityName").startAt(query).endAt(query +"\uf8ff"), communitymodel.class)
                                        .build();
                        adapter = new myadapter2(options);
                        adapter.startListening();
                        a.setAdapter(adapter);
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
                        a.setAdapter(adapter);
                        return false;
                    }
                });
            }
        });

        a = view.findViewById(R.id.recview3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);//to reverse the recycler view
        layoutManager.setStackFromEnd(true);//to reverse the recycler view
        a.setHasFixedSize(false);
        a.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Communities");


        FirebaseRecyclerOptions<communitymodel> options2 =
                new FirebaseRecyclerOptions.Builder<communitymodel>()
                        .setQuery(databaseReference, communitymodel.class)
                        .build();

        adapter = new myadapter2(options2);
        a.setAdapter(adapter);
        return view;
    }
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