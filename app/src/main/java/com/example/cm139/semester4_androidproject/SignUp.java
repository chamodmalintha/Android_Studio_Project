package com.example.cm139.semester4_androidproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cm139.semester4_androidproject.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText txtusername,txtname,txtphone,txtpassword;
    Button signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txtusername = (EditText)findViewById(R.id.txtsignupusername);
        txtname = (EditText)findViewById(R.id.txtsignupname);
        txtphone = (EditText)findViewById(R.id.txtsignupphone);
        txtpassword = (EditText)findViewById(R.id.txtsignuppass);

        signupbtn = (Button)findViewById(R.id.btnsignup);

        //Initializing Firebase

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check whether user phone number already exists or not
                        if (dataSnapshot.child(txtphone.getText().toString()).exists()){
                            Toast.makeText(SignUp.this, "User Already Registered!!! ", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            User user = new User(txtname.getText().toString(),txtpassword.getText().toString());
                            table_user.child(txtphone.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "New User Registered ", Toast.LENGTH_SHORT).show();
                            finish();

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
