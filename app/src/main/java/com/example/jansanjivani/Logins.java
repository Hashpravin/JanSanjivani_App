package com.example.jansanjivani;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Logins extends AppCompatActivity {

    private Button button  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

        // Simple User  Lo Button

        button = (Button)findViewById(R.id.SimpleUserLogin) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensimpleuserLog() ;
            }
        });

        // Blood Bank Login Button

        button = (Button)findViewById(R.id.BloodBankLogin) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodBankLogin() ;
            }
        });


    }

    public  void opensimpleuserLog(){
        Intent intent = new Intent(this,SimpleUserclass.class);
        startActivity(intent);
    }

    public  void openBloodBankLogin(){
        Intent intent = new Intent(this,BloodBankclass.class);
        startActivity(intent);
    }


    public void Admins(View view) {
        Intent intent = new Intent(this,App_Admins.class);
        startActivity(intent);
    }
}