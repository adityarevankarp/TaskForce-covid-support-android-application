package com.revankars.taskforce;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class CreateCommunityFragment extends Fragment {


    Button communityCreateButton;
    EditText communitynameEditText,communityPasswordEditText;
    AwesomeValidation awesomeValidation;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateCommunityFragment() {
        // Required empty public constructor
    }


    public static CreateCommunityFragment newInstance(String param1, String param2) {
        CreateCommunityFragment fragment = new CreateCommunityFragment();
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
        View view= inflater.inflate(R.layout.fragment_create_community, container, false);

        communityCreateButton = view.findViewById(R.id.createCommunityButton);
        communitynameEditText = view.findViewById(R.id.editTextTextPersonName);
        communityPasswordEditText = view.findViewById(R.id.editTextTextPersonName2);




        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(communitynameEditText,RegexTemplate.NOT_EMPTY,"Enter a Valid Name");

        /*awesomeValidation.addValidation(communityPasswordEditText,RegexTemplate.NOT_EMPTY,"Enter a Valid Password");*/

        awesomeValidation.addValidation(communityPasswordEditText,RegexTemplate.NOT_EMPTY,"Enter a Valid Password");












        communityCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(awesomeValidation.validate()){
                String communityPassword = communityPasswordEditText.getText().toString().replace(" ","");

                String communityName = communitynameEditText.getText().toString().toLowerCase();



                HashMap<String,Object> map = new HashMap<>();
                map.put("CommunityName" ,communityName);
                map.put("Password",communityPassword);



                FirebaseDatabase.getInstance().getReference("Communities").child(communityName)
                        .setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Your Community Created", Toast.LENGTH_SHORT).show();
                        /*AppCompatActivity activity = (AppCompatActivity)v.getContext();
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new CommunityListFragment()).addToBackStack(null).commit();*/
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("CommunityName",communityName);
                        startActivity(intent);
                    }
                });}else {
                    Toast.makeText(getContext(), "check your details again", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }


    


}