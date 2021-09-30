package com.example.jansanjivani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BloodBankclass extends AppCompatActivity {

    ProgressBar pb ;
    private Button button  ;
    TextInputEditText usereun ,userepass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bankclass);

        pb =(ProgressBar)findViewById(R.id.progressBar);
        usereun = (TextInputEditText) findViewById(R.id.Usernamesigninbb);
        userepass = (TextInputEditText) findViewById(R.id.passsigninbb);
        pb.setVisibility(View.GONE);

        // Signin Button
        button = (Button)findViewById(R.id.FinalSignup1) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isUser();
            }
        });

        // Newuser Button
        button = (Button)findViewById(R.id.New_User_Sign_Up1) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewUserSignup();
            }
        });

        // Forgotpassword Button
        button = (Button)findViewById(R.id.Forgotpass1) ;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Forgotpassword();
            }
        });
    }

    public void isUser( ){

        newfunc();

        }

    public void NewUserSignup(){
        Intent intent = new Intent(this,NewUserBloodBank.class);
        startActivity(intent);
    }

    public void Forgotpassword(){

        Intent intent = new Intent(this,ForgotPasswordBloodBank.class);
        startActivity(intent);
    }

    public void newfunc(){
        String userEnteredUsername = usereun.getText().toString().trim();
        String userEnteredpass = userepass.getText().toString().trim();

        Intent newintent = new Intent( this,SigninBloodBank.class) ;

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("user").equalTo(userEnteredUsername);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(snapshot.exists()){
                    pb.setVisibility(View.VISIBLE);

                    usereun.setError(null);

                    String passfromDB = snapshot.child(userEnteredUsername).child("pass").getValue(String.class);

                    if(passfromDB.equals(userEnteredpass)){
                        pb.setVisibility(View.GONE);

                        startActivity(newintent);
                    }
                    else{
                        pb.setVisibility(View.GONE);
                        userepass.setError("Wrong Password!!");
                        userepass.requestFocus();
                    }
                }
                else{
                    pb.setVisibility(View.GONE);
                    usereun.setError("User dosn't exist");
                    usereun.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}