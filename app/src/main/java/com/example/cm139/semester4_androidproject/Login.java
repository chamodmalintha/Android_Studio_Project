package com.example.cm139.semester4_androidproject;
import com.example.cm139.semester4_androidproject.Common.Common;
import com.example.cm139.semester4_androidproject.Model.User;
import com.google.firebase.analytics.FirebaseAnalytics;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firebase_core.*;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText phonenumber,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = (EditText)findViewById(R.id.txtloginpassword);
        phonenumber = (EditText)findViewById(R.id.txtloginphonenumber);
        login = (Button)findViewById(R.id.btnloginmain);

        //Initializing Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //Check whether user exists in database
                        if (dataSnapshot.child(phonenumber.getText().toString()).exists()) {

                            //Get User Information

                            User user = dataSnapshot.child(phonenumber.getText().toString()).getValue(User.class);

                            if (user.getPassword().equals(phonenumber.getText().toString())) {
                                Toast.makeText(Login.this, "Login Successfull ", Toast.LENGTH_SHORT).show();

                                Intent goToHome = new Intent(Login.this, Home.class);
                                //create variable to store current user
                                Common.currentUser = user;
                                startActivity(goToHome);


                                password.setText("");
                                phonenumber.setText("");
                                finish();

                            } else {
                                Toast.makeText(Login.this, "Login Failed!!! ", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else {
                            Toast.makeText(Login.this, "User not exists!!! ", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });







    }
}
