package com.hap.trip.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.util.FlightUtil;
import com.hap.trip.widget.FlightCardView;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.flight_card_view)
    FlightCardView flightCardView;

    public FlightHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(@Nonnull final FlightData flightData, @Nonnull final OnFlightClickListener onFlightClickListener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFlightClickListener.onClick(flightData);
            }
        });
        final FlightCardView.Builder cardBuilder = FlightUtil.getFlightResultCardBuilder(flightData);
        flightCardView.bind(cardBuilder);
    }

    public interface OnFlightClickListener {
        void onClick(final FlightData flightData);
    }
}