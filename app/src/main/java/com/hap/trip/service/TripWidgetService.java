package com.hap.trip.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.hap.trip.R;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.ui.detail.FlightDetailActivity;
import com.hap.trip.ui.widget.TripWidget;

import java.util.ArrayList;

public class TripWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TripListFactoryProvider(this.getApplicationContext());
    }
}

class TripListFactoryProvider implements RemoteViewsService.RemoteViewsFactory {
    private final Context context;
    // setup title of the recipe
    private ArrayList<FlightEntity> flightEntities = new ArrayList<>();

    TripListFactoryProvider(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        flightEntities.addAll(TripWidget.getFlights());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return flightEntities.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_flight_simple_view);
        final FlightEntity flightEntity = flightEntities.get(position);

        views.setTextViewText(R.id.tv_simple_header, flightEntity.getOutboundDate());
        views.setTextViewText(R.id.tv_simple_sub_header, flightEntity.getOutboundRoute());
        views.setTextViewText(R.id.tv_price, context.getString(R.string.flight_result_price_format, flightEntity.getPrice()));

        final Bundle args = new Bundle();
        args.putParcelable(FlightDetailActivity.ARG_FLIGHT_ENTITY_KEY, flightEntity);
        args.putBoolean(FlightDetailActivity.ARG_IS_FLIGHT_STORED_KEY, true);
        final Intent intent = new Intent();
        intent.putExtras(args);
        views.setOnClickFillInIntent(R.id.root, intent);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}