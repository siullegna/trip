package com.hap.trip.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.viewholder.AirportHolder;

import java.util.ArrayList;

public class AirportAdapter extends RecyclerView.Adapter<AirportHolder> {
    private final OnItemClickListener onItemClickListener;

    public AirportAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private final ArrayList<LocationAirportItem> airportItems = new ArrayList<>();

    public LocationAirportItem getItemByPosition(final int position) {
        return airportItems.get(position);
    }

    @NonNull
    @Override
    public AirportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(TripApplication.getInstance())
                .inflate(R.layout.item_flight_search, parent, false);

        return new AirportHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AirportHolder holder, int position) {
        final LocationAirportItem airportItem = getItemByPosition(position);
        holder.bind(airportItem, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(airportItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return airportItems.size();
    }

    public void addAll(final ArrayList<LocationAirportItem> airportItems) {
        this.airportItems.clear();
        this.airportItems.addAll(airportItems);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(final LocationAirportItem locationAirportItem);
    }
}
