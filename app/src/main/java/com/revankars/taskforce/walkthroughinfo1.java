package com.revankars.taskforce;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link walkthroughinfo1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class walkthroughinfo1 extends Fragment {

    ImageView nextImage;
    FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public walkthroughinfo1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment walkthroughinfo1.
     */
    // TODO: Rename and change types and number of parameters
    public static walkthroughinfo1 newInstance(String param1, String param2) {
        walkthroughinfo1 fragment = new walkthroughinfo1();
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
        View view =  inflater.inflate(R.layout.fragment_walkthroughinfo1, container, false);
        nextImage = view.findViewById(R.id.nextImageView);

        mAuth = FirebaseAuth.getInstance();

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper2,new walkthroughinfo2()).addToBackStack(null).commit();
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseUser mFirebaseuser = mAuth.getCurrentUser();

        if(mFirebaseuser != null){
            Intent intent = new Intent(getActivity(),CommunityActivity.class);

            startActivity(intent);
        }
    }
}