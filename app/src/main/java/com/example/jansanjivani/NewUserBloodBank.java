

package com.example.jansanjivani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class
NewUserBloodBank extends AppCompatActivity {
    private Button button;


    TextInputEditText na,una,pa,ad,ph ;
    FirebaseDatabase rootNode ;
    DatabaseReference reference  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_blood_bank);

        na = (TextInputEditText) findViewById(R.id.orgname);
        una = (TextInputEditText) findViewById(R.id.orgusername);
        pa = (TextInputEditText) findViewById(R.id.orgpass);
        ad = (TextInputEditText) findViewById(R.id.orgadress);
        ph = (TextInputEditText) findViewById(R.id.phonno);

        button = (Button) findViewById(R.id.CreatenewBB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                singinbloodbank();
            }
        });

        button = (Button) findViewById(R.id.AlreadyragisBB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBloodBankLogin();
            }
        });
    }

    public void openBloodBankLogin() {

        Intent intent = new Intent(this,BloodBankclass.class);
        startActivity(intent);
    }

    public  void singinbloodbank(){

        String name = na.getText().toString() ;
        String username = una.getText().toString();
        String pass = pa.getText().toString() ;
        String phone_no =  ph.getText().toString();
        String address = ad.getText().toString();


        rootNode = FirebaseDatabase.getInstance();

        String userEnteredUsername = una.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("user").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(!snapshot.exists()){

                    String bloods = "0" ;

                    NewuserHelperclass helperclass = new NewuserHelperclass(name,username,pass,phone_no,address) ;

                    reference.child(username).setValue(helperclass) ;
                    reference.child(username).child("bloods").setValue(bloods) ;

                    Intent extraintent = new Intent(NewUserBloodBank.this,SigninBloodBank.class);
                    extraintent.putExtra("usernamedb",username);
                    startActivity(extraintent);

                }
                else{
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}