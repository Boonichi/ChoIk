package com.android.vnnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.vnnv.model.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private static final String[] paths = {"customer", "shipper", "seller"};
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signuppage);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignupPage.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        TextView username =(TextView) findViewById(R.id.username);
        TextView email =(TextView) findViewById(R.id.email);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton signupbtn = (MaterialButton) findViewById(R.id.signupbtn);
        MaterialButton login = (MaterialButton) findViewById(R.id.login);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference users =  database.getReference("User");

        //Move to log in
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupPage.this, LoginPage.class);
                startActivity(intent);
            }
        });


        //LoginButton
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //Check if user not exist in database
                        if (snapshot.child("customer").child(username.getText().toString()).exists() || snapshot.child("shipper").child(username.getText().toString()).exists() || snapshot.child("seller").child(username.getText().toString()).exists()) {
                            Toast.makeText(SignupPage.this, "Username exist", Toast.LENGTH_SHORT).show();
                        } else {
                            //Add User information
                            User user = new User(email.getText().toString(), password.getText().toString());
                            users.child(type).child(username.getText().toString()).setValue(user);

                            Toast.makeText(SignupPage.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
                            Intent intent;
                            if (type.equals("customer") || type.equals("seller")) {
                                intent = new Intent(SignupPage.this, ChooseMarket.class);
                            }
                            else
                            {
                                intent = new Intent(SignupPage.this, FindOrder.class);
                            }
                            startActivity(intent);
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//            if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
//                //correct
//                Intent intent = new Intent(SignupPage.this, ChooseRestaurant.class);
//                startActivity(intent);
//            }
//            else
//                //incorrect
//                Toast.makeText(SignupPage.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                type = "customer";
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                type = "shipper";
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                type = "seller";
                // Whatever you want to happen when the second item gets selected
                break;
        }}
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
}