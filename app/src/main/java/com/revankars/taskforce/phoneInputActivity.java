package com.revankars.taskforce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class phoneInputActivity extends AppCompatActivity {
    CountryCodePicker countryCodePicker;
    EditText phonenumberinput;
    Button sendotp;
    FirebaseAuth mAuth;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_input);

        mAuth = FirebaseAuth.getInstance();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        phonenumberinput = findViewById(R.id.editTextPhoneinput);

        sendotp = findViewById(R.id.getotpbutton);
        countryCodePicker = (CountryCodePicker) findViewById(R.id.ccp);
        countryCodePicker.registerCarrierNumberEditText(phonenumberinput);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.editTextPhoneinput, RegexTemplate.NOT_EMPTY,R.string.enter_a_valid_phone);

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(awesomeValidation.validate()){
                Intent intent = new Intent(phoneInputActivity.this,otpInputActivity.class);
                intent.putExtra("mobile",countryCodePicker.getFullNumberWithPlus().replace("",""));
                startActivity(intent);}else {
                    Toast.makeText(phoneInputActivity.this, "Enter a valid phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();


        FirebaseUser mFirebaseuser = mAuth.getCurrentUser();

        if(mFirebaseuser != null){
            startActivity(new Intent(this,CommunityActivity.class));
        }

    }


}