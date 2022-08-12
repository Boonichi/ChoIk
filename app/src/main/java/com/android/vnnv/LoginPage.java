package com.android.vnnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vnnv.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_users =  database.getReference("User");

        //LoginButton
        loginbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(LoginPage.this, username.getText().toString(), Toast.LENGTH_SHORT).show();

            table_users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Check if user not exist in database
                    Toast.makeText(LoginPage.this, username.getText().toString(), Toast.LENGTH_SHORT).show();
                    System.out.print(username.getText().toString());

                    if (snapshot.child(username.getText().toString()).exists()) {
                        //Get User information
                        User user = snapshot.child(username.getText().toString()).getValue(User.class);
                        if (user.getPass().equals(password.getText().toString())) {
                            Toast.makeText(LoginPage.this, "Log in successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginPage.this, ChooseRestaurant.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginPage.this, "Log in failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginPage.this, "User not exist", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                //correct
//                Intent intent = new Intent(LoginPage.this, ChooseRestaurant.class);
//                startActivity(intent);
//            }
//            else
//                //incorrect
//                Toast.makeText(LoginPage.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();

        }
        });

    }
}