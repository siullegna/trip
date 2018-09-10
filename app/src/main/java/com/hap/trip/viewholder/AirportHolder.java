package com.hap.trip.viewholder;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.model.AirportItem;
import com.hap.trip.model.location.LocationAirportItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirportHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_airport)
    AppCompatTextView tvAirport;
    @BindView(R.id.tv_country)
    AppCompatTextView tvCountry;
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;

    public AirportHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final LocationAirportItem airportItem, final View.OnClickListener onClickListener) {
        tvAirport.setText(airportItem.getAirportName());

        tvCountry.setVisibility(View.VISIBLE);
        tvCountry.setText(airportItem.getCityName());
        ivIcon.setVisibility(View.GONE);

        itemView.setOnClickListener(onClickListener);
    }
}