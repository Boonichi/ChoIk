package com.android.vnnv;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //LoginButton
        loginbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                //correct
                Intent intent = new Intent(LoginPage.this, ChooseRestaurant.class);
                startActivity(intent);
            }
            else
                //incorrect
                Toast.makeText(LoginPage.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
        }
        });

    }
}