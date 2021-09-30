package com.example.jansanjivani;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Bloods_display extends AppCompatActivity {

    private ListView listView ;
    private ListView listview1 ;
    private Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloods_display);


        listView = (ListView)findViewById(R.id.listview) ;
        listview1= (ListView)findViewById(R.id.listview1) ;
        button = (Button)findViewById(R.id.Button);

        Intent extraintent = getIntent();
        String user_username = extraintent.getStringExtra("Uname") ;

        String[] Blood_groups = new String[] {"AB-","AB+","A-","A+","B-","B+","O-","O+"};


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ArrayList<String> list = new ArrayList<>();
                final ArrayAdapter adapter = new ArrayAdapter<String>(Bloods_display.this,R.layout.list_items,list);
                listView.setAdapter(adapter);

                final ArrayAdapter newadapter = new ArrayAdapter<String>(Bloods_display.this,R.layout.list_items,Blood_groups);
                listview1.setAdapter(newadapter);


                DatabaseReference new_ref = FirebaseDatabase.getInstance().getReference().child("users").child(user_username).child("bloods");
                new_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        list.clear();
                        for(DataSnapshot snapshot1 : snapshot.getChildren()){
                            list.add(snapshot1.getValue().toString());
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }
}