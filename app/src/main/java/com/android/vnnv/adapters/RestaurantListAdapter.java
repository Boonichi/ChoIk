package com.android.vnnv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.vnnv.R;
import com.android.vnnv.model.MarketModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private List<MarketModel> marketModelList;
    private RestaurantListClickListener clickListener;

    public RestaurantListAdapter(List<MarketModel> marketModelList, RestaurantListClickListener clickListener) {
        this.marketModelList = marketModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<MarketModel> marketModelList) {
        this.marketModelList = marketModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, int position) {
        holder.restaurantName.setText(marketModelList.get(position).getName());
        holder.restaurantAddress.setText("Address: "+ marketModelList.get(position).getAddress());
        holder.restaurantHours.setText("Today's hours: " + marketModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(marketModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(marketModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return marketModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  restaurantName;
        TextView  restaurantAddress;
        TextView  restaurantHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            restaurantName = view.findViewById(R.id.restaurantName);
            restaurantAddress = view.findViewById(R.id.restaurantAddress);
            restaurantHours = view.findViewById(R.id.restaurantHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface RestaurantListClickListener {
        public void onItemClick(MarketModel marketModel);
    }
}
