package com.hap.trip.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.persistence.room.entity.FlightEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyFlightHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_simple_header)
    TextView tvHeader;
    @BindView(R.id.tv_simple_sub_header)
    TextView tvSubHeader;
    @BindView(R.id.tv_price)
    TextView tvPrice;

    public MyFlightHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final FlightEntity flightEntity) {
        tvHeader.setText(flightEntity.getOutboundDate());
        tvSubHeader.setText(flightEntity.getOutboundRoute());
        tvPrice.setText(TripApplication.getInstance().getString(R.string.flight_result_price_format, flightEntity.getPrice()));
    }
}
