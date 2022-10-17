package com.revankars.taskforce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HelpActivity extends AppCompatActivity {

    private EditText name,surname,Age,PlaceStay,Adress,phone,attender,dependents,SRFNO,BUID,AlsoHelp;
    private Button save;
    private Spinner spinnerGender,spinnerZone,spinnerBloodGroup,spinnerhelp;
    AwesomeValidation awesomeValidation;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);



        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AlsoHelp = findViewById(R.id.AlsoHelpText);
        save = findViewById(R.id.save);
        name = findViewById(R.id.editTextPatientName);


        surname = findViewById(R.id.editTextPatientSurname);//empty
        Age = findViewById(R.id.editTextAge);//empty
        PlaceStay = findViewById(R.id.editTextPlaceOfStay);//empty
        Adress = findViewById(R.id.editTextAdress);//empty
        spinnerZone = findViewById(R.id.spinnerZone);
        spinnerBloodGroup = findViewById(R.id.spinnerBloodGroup);
        spinnerhelp = findViewById(R.id.spinnerHelpRequiredOn);//empty
        phone = findViewById(R.id.editTextPhone);//phone number 10
        attender = findViewById(R.id.editTextAttender);////empty
        dependents = findViewById(R.id.editTextDependents);//empty
        SRFNO = findViewById(R.id.editTextSRFID);
        BUID = findViewById(R.id.editTextBUno);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.editTextPatientName, RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextPatientSurname,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextAge,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextPlaceOfStay,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextAdress,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextAttender,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextDependents,RegexTemplate.NOT_EMPTY,R.string.field_cannot_be_empty);
        awesomeValidation.addValidation(this,R.id.editTextPhone,"[5-9]{1}[0-9]{9}$",R.string.enter_a_valid_phone);








        List<String> gender = Arrays.asList("Male","Female");

        List<String> Zone = Arrays.asList("Yelahanka","Dasarahalli","Rajarajeshwarinagar",
                "Bommanahalli","South (Basavanagudi, jayanagar, hanumathanagar)",
                "West (Basaveshwarnagar, Rajajinagar, Mysore road)","East (Marathahalli, Old Airport Road,Banaswadi)"
                ,"North (Malleshwaram, RT Nagar)");

        List<String> BloodGroup = Arrays.asList("A+","A-","B+","B-","O+","O-","AB+","AB-");

        List<String> HelpOn = Arrays.asList("System Profile of the RTPCR test :","Notification at BBMP :",
                "Hospital Bed / Bed Availability Profile:",
                "Food Support for Covid Patients:",
                "Medicine support","Registration for the covid Vaccine at the nearest location:",
                "Doctors Advice:","Counselling","Oxygen Cylinder Support","Plasma"
                ,"None of these");



        spinnerGender = findViewById(R.id.spinnerGender);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,gender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        spinnerZone = findViewById(R.id.spinnerZone);
        ArrayAdapter adapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,Zone);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerZone.setAdapter(adapter2);

        spinnerBloodGroup = findViewById(R.id.spinnerBloodGroup);
        ArrayAdapter adapter3 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,BloodGroup);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapter3);

        spinnerhelp = findViewById(R.id.spinnerHelpRequiredOn);
        ArrayAdapter adapter4 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item,HelpOn);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerhelp.setAdapter(adapter4);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(awesomeValidation.validate()) {


                    String gender = spinnerGender.getSelectedItem().toString();
                    String Zone = spinnerZone.getSelectedItem().toString();
                    String bloodgroup = spinnerBloodGroup.getSelectedItem().toString();
                    String Helpon = spinnerhelp.getSelectedItem().toString();


                    HashMap<String, Object> map = new HashMap<>();

                    map.put("Gender", gender);
                    map.put("Zone", Zone);
                    map.put("BloodGroup", bloodgroup);
                    map.put("HelpOn", Helpon);
                    map.put("Name", name.getText().toString().toLowerCase());
                    map.put("Surname", surname.getText().toString().toLowerCase());
                    map.put("Age", Age.getText().toString());
                    map.put("PlaceOfStay", PlaceStay.getText().toString());
                    map.put("Address", Adress.getText().toString());
                    map.put("Phone", phone.getText().toString());
                    map.put("Attender", attender.getText().toString());
                    map.put("Dependents", dependents.getText().toString());
                    map.put("BUnum6digi", BUID.getText().toString());
                    map.put("SRFID13Nos", SRFNO.getText().toString());
                    map.put("AlsoHelp", AlsoHelp.getText().toString());



                    /*map.put("Surname" ,surname);*//*
                map.put("Gender",gender);
                map.put("Age" ,Age.getText().toString());
                map.put("PlaceOfStay" ,PlaceStay.getText().toString());
                map.put("Address",Adress.getText().toString());
                map.put("Phone",phone.getText().toString());
                map.put("Attender",attender.getText().toString());
                map.put("Dependents",dependents.getText().toString());
                map.put("Zone",Zone);
                map.put("BloodGroup",bloodgroup);
                map.put("HelpOn",Helpon);
                map.put("BUnum6digi",BUID.getText().toString());
                map.put("SRFID13Nos",SRFNO.getText().toString());
                map.put("AlsoHelp",AlsoHelp.getText().toString());*/

                    Intent intent = getIntent();
                    String CommunityNameNameName = intent.getStringExtra("CommunityName");

                    FirebaseDatabase.getInstance().getReference("Communities").child(CommunityNameNameName).child(CommunityNameNameName).push()
                            .setValue(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(HelpActivity.this, "Your detailes has been uploaded", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(HelpActivity.this, MainActivity.class);
                                    i.putExtra("CommunityName", CommunityNameNameName);
                                    startActivity(i);

                                }

                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("failed", "failed" + e.toString());
                        }
                    });
                }


            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done"/*getString(R.string.msg_subscribed*/;
                        if (!task.isSuccessful()) {
                            msg = "failed";
                        }

                    }
                });



    }





}