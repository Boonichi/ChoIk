package com.android.vnnv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.vnnv.model.Item_list;
import com.android.vnnv.model.Order;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderStatus extends AppCompatActivity {
    private static final String[] market = {"Cho Con", "Cho Han", "Cho Bac My An", "Cho Dau Moi Hoa Cuong", "Cho Dem Son Tra", "Cho Troi", "Cho Nai Hien", "Cho Moi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_layout);

        TextView address =(TextView) findViewById(R.id.order_address);
        TextView status =(TextView) findViewById(R.id.order_status);
        TextView market =(TextView) findViewById(R.id.order_market);
        TextView price =(TextView) findViewById(R.id.order_price);

        MaterialButton donebtn = (MaterialButton) findViewById(R.id.donebtn);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_order =  database.getReference("Orders");
        final DatabaseReference table_list_item =  database.getReference("List_item");

        donebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                table_order.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Order order = snapshot.getValue(Order.class);
                        order.setStatus("Done");
                        status.setText(order.getStatus());
                        table_order.setValue(order);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        startActivity(new Intent(OrderStatus.this, FindOrder.class));
                    }
                });
            }
        });

        table_order.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Order order = snapshot.getValue(Order.class);
                address.setText(order.getAddress());
                status.setText(order.getStatus());
                market.setText(order.getMarket());
                price.setText(order.getPrice());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}