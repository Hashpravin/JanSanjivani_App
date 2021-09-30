package com.example.jansanjivani;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class admin_password extends AppCompatActivity {

    EditText pass;

    String extract ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_password);


        pass = (EditText)findViewById(R.id.pass);
        Button beg = (Button)findViewById(R.id.button);
        extract = pass.getText().toString();
        beg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extract = pass.getText().toString();
                if(extract == "1234"){
                    Intent intent = new Intent(admin_password.this,App_Admins.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Password "+ extract
                             , Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}