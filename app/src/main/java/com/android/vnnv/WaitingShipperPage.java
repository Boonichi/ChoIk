package com.android.vnnv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.content.Intent;
import com.android.vnnv.model.MarketModel;
import com.android.vnnv.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.android.vnnv.model.Order;
import com.android.vnnv.model.Menu;


public class WaitingShipperPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_shipper_page);

        MarketModel marketModel = getIntent().getParcelableExtra("RestaurantModel");
        Bundle extras = getIntent().getExtras();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(marketModel.getName());
        actionBar.setSubtitle(marketModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView comment = (TextView)findViewById(R.id.textView);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference status =  database.getReference("Orders");
        status.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.exists()) {
                    comment.setText("Looking for Shipper");
                    Order order = new Order("VTC Acedemy", marketModel.getName(), extras.getString("price"), "Active");
                    status.setValue(order);
                }
                else if (snapshot.child("status").equals("Ongoing")){
                    comment.setText("Your shipper got your Orders");
                }
                else if (snapshot.child("status").equals("Done")){
                    comment.setText("Order Sucessfully");
                };
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        TextView buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                status.removeValue();
                finish();
            }
        });
    }
}