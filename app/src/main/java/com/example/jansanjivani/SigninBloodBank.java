package com.example.jansanjivani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SigninBloodBank extends AppCompatActivity {

     EditText  ap,bp,op,abp,an,bn,on,abn ;
     Button button ;
     FirebaseDatabase rootNode ;
     DatabaseReference reference ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_blood_bank);

        an = (EditText)findViewById(R.id.An);
        bn = (EditText)findViewById(R.id.Bn);
        on = (EditText)findViewById(R.id.On);
        abn= (EditText)findViewById(R.id.ABn);


        ap =(EditText) findViewById(R.id.Ap);
        bp = (EditText)findViewById(R.id.Bp);
        op = (EditText)findViewById(R.id.Op);
        abp= (EditText)findViewById(R.id.ABp);




        button = (Button)findViewById(R.id.SaveBloodData);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datafunc();
            }
        });
    }



    public void datafunc(){

        Intent extraintent = getIntent();
        String user_username = extraintent.getStringExtra("usernamedb") ;




        rootNode = FirebaseDatabase.getInstance() ;
        reference = rootNode.getReference("users")  ;




        String  An =  an.getText().toString().trim() ;
        String  Bn =  bn.getText().toString().trim();
        String  On =  on.getText().toString().trim() ;
        String ABn = abn.getText().toString().trim();

        String  Ap =  ap.getText().toString().trim() ;
        String Bp = bp.getText().toString().trim();
        String  Op =  op.getText().toString().trim() ;
        String ABp = abn.getText().toString().trim();



        Datasavehelper newhelp = new Datasavehelper(Ap,An,Bp,Bn,Op,On,ABp,ABn) ;
        reference.child(user_username).child("bloods").setValue(newhelp);

    }

}