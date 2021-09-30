package com.example.jansanjivani;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SimpleUserclass extends AppCompatActivity {


    private ListView listView ;
    private Button button ;


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    // GPS Class'''''''''''''''''''''''''''''''''''''''''''''''''''''''''

    GPSTracker gps;
    private PackageManager MockPackageManager;

    public SimpleUserclass() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_userclass);

        // GPS Activity Starts

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                //execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        gps = new GPSTracker(this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            double userlatitude = gps.getLatitude();
            double userlongitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                    + userlatitude + "\nLong: " + userlongitude, Toast.LENGTH_LONG).show();

        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
        // GPS Activity Ends'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''




        listView = (ListView)findViewById(R.id.listview) ;
        button = (Button)findViewById(R.id.Button);

        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view)  {

                double userlatitude = gps.getLatitude();
                double userlongitude = gps.getLongitude();


                final ArrayList<Float> latilist = new ArrayList<>();
                final ArrayList<Float> longilist = new ArrayList<>();
                final ArrayList<String> ulist = new ArrayList<>();
                final ArrayList<Double> dists = new ArrayList<>();
                HashMap <Double, String> u_d = new HashMap<>();

                final ArrayAdapter adapter = new ArrayAdapter<String>(SimpleUserclass.this,R.layout.list_items,ulist);

                listView.setAdapter(adapter);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        longilist.clear();
                        latilist.clear();
                        dists.clear();
                        ulist.clear();
                        u_d.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                            users info = snapshot.getValue(users.class);
                            Longi_and_lati_helper ll = snapshot.child("location").getValue(Longi_and_lati_helper.class);

                            float la = Float.parseFloat(ll.getLati());
                            float lo = Float.parseFloat(ll.getLongi());

                            latilist.add(la);
                            longilist.add(lo);

                            String currentUser = info.getUser().toString();

                            String unam = info.getUser().toString();



                            double distance =distCalci(userlongitude,userlatitude,lo,la);

                            dists.add(distance);
                            u_d.put(distance,unam);


                            Collections.sort(dists);

                        }

                        for(Double d :dists){
                            ulist.add(u_d.get(d));
                        }
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Intent bloodintent = new Intent(SimpleUserclass.this,Bloods_display.class);
                        bloodintent.putExtra("Uname", ulist.get(i).toString());
                        startActivity(bloodintent);
                    }
                });
            }
        });
    }


    // A method to calculate the shotest distance between tow points if there longitude and latitude are given By using Haversine's formula.

    public  static double distCalci(double lon1,double lat1, double lon2,  double lat2){


        // lon1 - user longitude
        // lat1 - user latitude
        // lon2 - blood bank longitude
        // lat2 - blood bank latitude

        



            // The math module contains a function

            // named toRadians which converts from

            // degrees to radians.

            lon1 = Math.toRadians(lon1);

            lon2 = Math.toRadians(lon2);

            lat1 = Math.toRadians(lat1);

            lat2 = Math.toRadians(lat2);


            // Haversine formula

            double dlon = lon2 - lon1;

            double dlat = lat2 - lat1;

            double a = Math.pow(Math.sin(dlat / 2), 2)

                    + Math.cos(lat1) * Math.cos(lat2)

                    * Math.pow(Math.sin(dlon / 2),2);


            double c = 2 * Math.asin(Math.sqrt(a));


            // Radius of earth in kilometers. Use 6371

            // for miles use r = 3959

            double r = 6371;

            // calculate the result

            return(c * r);
        }

}




