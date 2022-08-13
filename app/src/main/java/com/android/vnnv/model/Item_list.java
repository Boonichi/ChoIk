package com.android.vnnv.model;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class Item_list {
    private Map<String, Integer> item_list;
    public class Item {
        private String name;
        private double price;
        private String url;

        public Item(String name, double price, String url) {
            this.name = name;
            this.price = price;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getUrl() {
            return url;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public Item_list(Map<String, Integer> num_list) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference menu =  database.getReference("Menu");
        menu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (Map.Entry<String, Integer> entry: num_list.entrySet()) {
                    Item item = snapshot.child(entry.getKey()).getValue(Item.class);
                    item_list.put(item.getName(), entry.getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
