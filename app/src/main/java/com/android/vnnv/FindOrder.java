package com.android.vnnv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FindOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shipper_find_order);

        MaterialButton findbtn = (MaterialButton) findViewById(R.id.findbtn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order =  database.getReference("Orders");
        findbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                table_order.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            startActivity(new Intent(FindOrder.this, OrderStatus.class));
                        }
                        else{
                            Toast.makeText(FindOrder.this, "Order Not Available", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
