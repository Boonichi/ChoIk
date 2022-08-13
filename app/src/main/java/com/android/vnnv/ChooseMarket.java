package com.android.vnnv;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.vnnv.adapters.MarketListAdapter;
import com.android.vnnv.model.MarketModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class ChooseMarket extends AppCompatActivity implements MarketListAdapter.RestaurantListClickListener {

    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_market);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Market List");

        List<MarketModel> marketModelList =  getRestaurantData();

        initRecyclerView(marketModelList);
    }

    private void initRecyclerView(List<MarketModel> marketModelList) {
        RecyclerView recyclerView =  findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MarketListAdapter adapter = new MarketListAdapter(marketModelList, this);
        recyclerView.setAdapter(adapter);
    }

    private List<MarketModel> getRestaurantData() {
        InputStream is = getResources().openRawResource(R.raw.market);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try{
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while(( n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0,n);
            }
        }catch (Exception e) {

        }

        String jsonStr = writer.toString();
        Gson gson = new Gson();
        MarketModel[] marketModels =  gson.fromJson(jsonStr, MarketModel[].class);
        List<MarketModel> restList = Arrays.asList(marketModels);

        return  restList;

    }

    @Override
    public void onItemClick(MarketModel marketModel) {
        Intent intent = new Intent(ChooseMarket.this, MarketMenuActivity.class);
        intent.putExtra("RestaurantModel", marketModel);
        startActivity(intent);

    }
}