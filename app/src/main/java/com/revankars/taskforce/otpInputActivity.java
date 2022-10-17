package com.revankars.taskforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpInputActivity extends AppCompatActivity {
    Button verifybutton;
    EditText otpinput;
    String phonenumber;
    String otpid;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_input);

        phonenumber = getIntent().getStringExtra("mobile").toString();

        verifybutton = findViewById(R.id.verifyotpbutton);
        otpinput = findViewById(R.id.optenteredittext);
        mAuth = FirebaseAuth.getInstance();

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        
        initiateotp();

        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otpinput.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Black field cannot be processed", Toast.LENGTH_SHORT).show();
                }else if(otpinput.getText().toString().length()!=6){
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_SHORT).show();
                }else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,otpinput.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });


            

    }

    private void initiateotp() {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(FirebaseAuth.getInstance())
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                /*super.onCodeSent(s, forceResendingToken);  */

                                otpid = s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                signInWithPhoneAuthCredential(phoneAuthCredential);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otpInputActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(otpInputActivity.this,CommunityActivity.class));
                            finish();



                        } else {

                            Toast.makeText(otpInputActivity.this, "incorrect otp", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}