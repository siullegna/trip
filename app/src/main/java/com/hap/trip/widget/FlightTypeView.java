package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightTypeView extends LinearLayout {
    @BindView(R.id.one_way)
    AppCompatTextView oneWay;
    @BindView(R.id.round_trip)
    AppCompatTextView roundTrip;

    public FlightTypeView(Context context) {
        this(context, null);
    }

    public FlightTypeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlightTypeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.flight_type_view, this);
        ButterKnife.bind(this, view);
    }

    public void setupListener(final OnFlightListener onFlightListener) {
        oneWay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFlightListener.oneWay();
            }
        });

        roundTrip.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onFlightListener.roundTrip();
            }
        });
    }

    public interface OnFlightListener {
        void oneWay();

        void roundTrip();
    }
}
