package com.android.vnnv;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.vnnv.model.MarketModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WaitingShipperPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_shipper_page);


        MarketModel marketModel = getIntent().getParcelableExtra("RestaurantModel");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(marketModel.getName());
        actionBar.setSubtitle(marketModel.getAddress());
        actionBar.setDisplayHomeAsUpEnabled(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_users =  database.getReference("CustomerPendingStatus");

        Boolean CustomerPendingStatus = true;

        table_users.setValue(CustomerPendingStatus);

        TextView buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}