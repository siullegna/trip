package com.hap.trip.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.persistence.FlightContract;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.util.IntentFactory;

import java.util.ArrayList;

public class TripWidget extends AppWidgetProvider {
    public static void updateAppWidget(final Context context, final AppWidgetManager appWidgetManager, final int appWidgetId) {
        // construct the RemoteViews object
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.trip_widget);

        // Intent for the service - Add the app widget ID to the intent extras.
        final Intent intent = IntentFactory.getWidgetServiceIntent(appWidgetId);
        views.setRemoteAdapter(R.id.lv_flights, intent);

        final Intent appIntent = IntentFactory.getFlightDetailIntent();
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.lv_flights, pendingIntent);

        views.setEmptyView(R.id.lv_flights, R.id.empty_screen);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    public static ArrayList<FlightEntity> getFlights() {
        final ArrayList<FlightEntity> flightEntities = new ArrayList<>();

        final Uri queryUri = FlightContract.FlightEntity.CONTENT_URI;
        final Cursor cursor = TripApplication.getInstance().getContentResolver()
                .query(queryUri,
                        null,
                        null,
                        null,
                        null);
        try {
            if (cursor == null || cursor.getCount() == 0) {
                return flightEntities;
            }

            while (cursor.moveToNext()) {
                final FlightEntity flightEntity = FlightEntity.fromCursor(cursor);
                flightEntities.add(flightEntity);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return flightEntities;
    }
}
