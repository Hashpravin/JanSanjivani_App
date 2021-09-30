package com.example.jansanjivani;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class App_Admins extends AppCompatActivity {

    EditText username,longi,lati;
    Button but ;

    FirebaseDatabase rootNode ;
    DatabaseReference reference  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_admins);

        username =(EditText)findViewById(R.id.user) ;
        longi = (EditText)findViewById(R.id.Longitude);
        lati = (EditText)findViewById(R.id.Latitude);

        but = (Button)findViewById(R.id.submit);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_data();
            }
        });
    }

    public void send_data(){

        rootNode = FirebaseDatabase.getInstance() ;
        reference = rootNode.getReference("users")  ;

        String Longitude = longi.getText().toString().trim();
        String Latitude = lati.getText().toString().trim();
        String u = username.getText().toString();

        String location = "0";

        Longi_and_lati_helper newhelp = new Longi_and_lati_helper(Longitude,Latitude) ;
        reference.child(u).child("location").setValue(newhelp);
    }
}