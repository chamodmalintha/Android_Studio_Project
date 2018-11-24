package com.example.cm139.semester4_androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnsignup,btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnsignup = (Button)findViewById(R.id.btnmainsignup);
        btnlogin = (Button)findViewById(R.id.btnmainlogin);

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(goToSignUp);

            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent goToLogin = new Intent(MainActivity.this, Login.class);
                startActivity(goToLogin);

            }
        });


    }
}
