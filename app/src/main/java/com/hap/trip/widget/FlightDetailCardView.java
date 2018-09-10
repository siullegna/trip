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
import com.hap.trip.model.flight.Inbound;
import com.hap.trip.model.flight.Outbound;
import com.hap.trip.persistence.room.converter.FlightConverter;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.util.FlightUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightDetailCardView extends LinearLayout {
    @BindView(R.id.tv_departing_from)
    AppCompatTextView tvDepartingFrom;
    @BindView(R.id.departing_from_card)
    LinearLayout departFromCard;
    @BindView(R.id.tv_returning_from)
    AppCompatTextView tvReturningFrom;
    @BindView(R.id.returning_from_card)
    LinearLayout returningFromCard;

    public FlightDetailCardView(Context context) {
        this(context, null);
    }

    public FlightDetailCardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlightDetailCardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.flight_detail_card_view, this);
        ButterKnife.bind(this, view);
    }

    public void bind(final FlightEntity flightEntity) {
        tvDepartingFrom.setText(getContext().getString(R.string.flight_depart_from, flightEntity.getOutboundRoute(), flightEntity.getOutboundDuration(), flightEntity.getPrice()));

        departFromCard.removeAllViews();
        final ArrayList<FlightCardView.Builder> departingBuilderList = FlightUtil.getOutboundBuilder(FlightConverter.fromString(flightEntity.getOutboundFlights()));
        for (final FlightCardView.Builder builder : departingBuilderList) {
            final FlightCardView flightCardView = new FlightCardView(getContext());
            flightCardView.bind(builder);
            departFromCard.addView(flightCardView);
        }

        returningFromCard.removeAllViews();
        if (TextUtils.isEmpty(flightEntity.getInboundFlights())) {
            tvReturningFrom.setVisibility(GONE);
            return;
        }
        final ArrayList<FlightCardView.Builder> arrivingBuilderList = FlightUtil.getInboundBuilder(FlightConverter.fromString(flightEntity.getInboundFlights()));
        if (arrivingBuilderList.isEmpty()) {
            tvReturningFrom.setVisibility(GONE);
        } else {
            tvReturningFrom.setText(getContext().getString(R.string.flight_return_from, flightEntity.getInboundRoute(), flightEntity.getInboundDuration()));
            tvReturningFrom.setVisibility(VISIBLE);
            for (final FlightCardView.Builder builder : arrivingBuilderList) {
                final FlightCardView flightCardView = new FlightCardView(getContext());
                flightCardView.bind(builder);
                returningFromCard.addView(flightCardView);
            }
        }
    }
}
