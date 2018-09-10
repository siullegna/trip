package com.hap.trip.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.R;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.viewholder.MyFlightHolder;

import java.util.ArrayList;
import java.util.List;

public class MyFlightsAdapter extends RecyclerView.Adapter<MyFlightHolder> {
    private final ArrayList<FlightEntity> flightEntities = new ArrayList<>();
    private final OnFlightClickListener onFlightClickListener;

    public MyFlightsAdapter(OnFlightClickListener onFlightClickListener) {
        this.onFlightClickListener = onFlightClickListener;
    }

    @NonNull
    @Override
    public MyFlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight_simple_view, parent, false);
        return new MyFlightHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFlightHolder holder, int position) {
        final FlightEntity flightEntity = flightEntities.get(position);
        holder.bind(flightEntity);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFlightClickListener.onClick(flightEntity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return flightEntities.size();
    }

    public void addAll(final List<FlightEntity> flightEntities) {
        this.flightEntities.clear();
        this.flightEntities.addAll(flightEntities);
        notifyDataSetChanged();
    }

    public interface OnFlightClickListener {
        void onClick(final FlightEntity flightEntity);
    }
}
