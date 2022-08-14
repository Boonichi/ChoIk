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
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.android.vnnv.model.Order;
import com.android.vnnv.model.Menu;


public class OrderSucessfully extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_shipper_page);

        MaterialButton DoneButton = (MaterialButton) findViewById(R.id.buttonDone);

        DoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSucessfully.this, PlaceYourOrderActivity.class);
                startActivity(intent);
            }
        });


    }
}