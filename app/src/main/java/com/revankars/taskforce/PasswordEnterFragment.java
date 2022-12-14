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


public class PasswordEnterFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    String CommunityName,Password;
    EditText passwordInput,passwordedittext;
    Button joinButton;



    public PasswordEnterFragment() {
        // Required empty public constructor
    }

    public PasswordEnterFragment(String CommunityName,String Password) {
        this.CommunityName = CommunityName;
        this.Password = Password;
    }



    public static PasswordEnterFragment newInstance(String param1, String param2) {
        PasswordEnterFragment fragment = new PasswordEnterFragment();
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
        View view = inflater.inflate(R.layout.fragment_password_enter, container, false);


        passwordInput = view.findViewById(R.id.passwordInputEditText);
        joinButton = view.findViewById(R.id.joinButton);

        if(CommunityName.equals("Public Community")){

            Intent intent = new Intent(getActivity(),MainActivity.class);
            intent.putExtra("CommunityName",CommunityName);
            startActivity(intent);
        }else {
            joinButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String passwordInputByUser = passwordInput.getText().toString();
                    if(passwordInputByUser.equals(Password)){
                        Toast.makeText(getContext(), "Password correct", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(),MainActivity.class);
                        intent.putExtra("CommunityName",CommunityName);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getContext(), "IncorrectPassword", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }





        return view;
    }
}