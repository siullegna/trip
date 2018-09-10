package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;
import com.hap.trip.model.flight.FlightData;
import com.hap.trip.util.FlightUtil;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightCardView extends LinearLayout {
    @BindView(R.id.tv_price)
    AppCompatTextView tvPrice;
    @BindView(R.id.tv_flight_time)
    AppCompatTextView tvFlightTime;
    @BindView(R.id.tv_airline)
    AppCompatTextView tvAirline;
    @BindView(R.id.tv_duration)
    AppCompatTextView tvDuration;
    @BindView(R.id.tv_type)
    AppCompatTextView tvType;
    @BindView(R.id.tv_flight_number)
    AppCompatTextView tvFlightNumber;
    @BindView(R.id.divider)
    View divider;

    public FlightCardView(Context context) {
        this(context, null);
    }

    public FlightCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlightCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.flight_card_view, this);
        ButterKnife.bind(this, view);
    }

    public void bind(@Nonnull final Builder builder) {
        if (TextUtils.isEmpty(builder.price)) {
            tvPrice.setVisibility(GONE);
        } else {
            tvPrice.setVisibility(VISIBLE);
            tvPrice.setText(builder.price);
        }

        if (TextUtils.isEmpty(builder.flightTime)) {
            tvFlightTime.setVisibility(GONE);
        } else {
            tvFlightTime.setVisibility(VISIBLE);
            tvFlightTime.setText(builder.flightTime);
        }

        if (TextUtils.isEmpty(builder.airline)) {
            tvAirline.setVisibility(GONE);
        } else {
            tvAirline.setVisibility(VISIBLE);
            tvAirline.setText(builder.airline);
        }

        if (TextUtils.isEmpty(builder.duration)) {
            tvDuration.setVisibility(GONE);
        } else {
            tvDuration.setVisibility(VISIBLE);
            tvDuration.setText(builder.duration);
        }

        if (TextUtils.isEmpty(builder.classType)) {
            tvType.setVisibility(GONE);
        } else {
            tvType.setVisibility(VISIBLE);
            tvType.setText(builder.classType);
        }

        if (TextUtils.isEmpty(builder.flightNumber)) {
            tvFlightNumber.setVisibility(GONE);
        } else {
            tvFlightNumber.setVisibility(VISIBLE);
            tvFlightNumber.setText(builder.flightNumber);
        }

        if (builder.isDivider) {
            divider.setVisibility(VISIBLE);
        } else {
            divider.setVisibility(GONE);
        }
    }

    public static class Builder {
        private final Context context;
        private String price;
        private String flightTime;
        private String airline;
        private String duration;
        private String classType;
        private String flightNumber;
        private boolean isDivider = true;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPrice(final String price) {
            this.price = context.getString(R.string.flight_result_price_format, price);
            return this;
        }

        public Builder setFlightTime(final String flightTime) {
            this.flightTime = flightTime;
            return this;
        }

        public Builder setAirline(final String airline) {
            this.airline = airline;
            return this;
        }

        public Builder setDuration(final String duration) {
            this.duration = duration;
            return this;
        }

        public Builder setClassType(final String classType) {
            this.classType = classType;
            return this;
        }

        public Builder setFlightNumber(final String flightNumber) {
            this.flightNumber = flightNumber;
            return this;
        }

        public Builder removeDivider() {
            this.isDivider = false;
            return this;
        }

        public Builder addDivider() {
            this.isDivider = true;
            return this;
        }

        public Builder build() {
            return this;
        }
    }
}
