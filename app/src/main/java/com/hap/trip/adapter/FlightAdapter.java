package com.hap.trip.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.viewholder.FlightHolder;

import java.util.ArrayList;

public class FlightAdapter extends RecyclerView.Adapter<FlightHolder> {
    private final ArrayList<FlightData> flightDataList = new ArrayList<>();
    private final FlightHolder.OnFlightClickListener onFlightClickListener;

    public FlightAdapter(FlightHolder.OnFlightClickListener onFlightClickListener) {
        this.onFlightClickListener = onFlightClickListener;
    }

    private FlightData getItemByPosition(final int position) {
        return flightDataList.get(position);
    }

    @NonNull
    @Override
    public FlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(TripApplication.getInstance()).inflate(R.layout.item_flight_result, parent, false);
        return new FlightHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightHolder holder, int position) {
        final FlightData flightData = getItemByPosition(position);
        holder.bind(flightData, onFlightClickListener);
    }

    @Override
    public int getItemCount() {
        return flightDataList.size();
    }

    public void addAll(final ArrayList<FlightData> flightDataList) {
        this.flightDataList.clear();
        this.flightDataList.addAll(flightDataList);
        notifyDataSetChanged();
    }
}
