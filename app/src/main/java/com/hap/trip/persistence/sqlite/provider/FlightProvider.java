package com.hap.trip.persistence.sqlite.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.hap.trip.persistence.FlightContract;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.persistence.sqlite.DatabaseHelper;

public class FlightProvider extends ContentProvider {
    private static final int CODE_ALL_FLIGHTS = 1001;
    private static final int CODE_SINGLE_FLIGHT = 1002;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private DatabaseHelper databaseHelper;

    public static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = FlightContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, FlightContract.PATH_FLIGHTS, CODE_ALL_FLIGHTS);
        matcher.addURI(authority, FlightContract.PATH_FLIGHTS + "/#", CODE_SINGLE_FLIGHT);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = databaseHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case CODE_ALL_FLIGHTS:
            case CODE_SINGLE_FLIGHT:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }

                db.beginTransaction();
                int rowsInserted = 0;
                try {
                    final long _id = db.insert(FlightContract.FlightEntity.TBL_FLIGHT, null, values);
                    if (_id != -1) {
                        rowsInserted++;
                    }
                } finally {
                    db.endTransaction();
                }
                if (rowsInserted > 0) {
                    context.getContentResolver().notifyChange(uri, null);
                    final FlightEntity flightEntity = FlightEntity.fromContentValues(values);
                    return ContentUris.withAppendedId(uri, flightEntity.getId());
                }
                break;
            default:
                throw new IllegalArgumentException("Uknown URI: " + uri);
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        switch (sUriMatcher.match(uri)) {
            case CODE_ALL_FLIGHTS:
            case CODE_SINGLE_FLIGHT:
                for (final ContentValues contentValues : values) {
                    insert(uri, contentValues);
                }
                return values.length;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case CODE_ALL_FLIGHTS:
                final Context context = getContext();
                if (context == null) {
                    return null;
                }

                try {
                    cursor = databaseHelper.getReadableDatabase()
                            .query(FlightContract.FlightEntity.TBL_FLIGHT,
                                    projection,
                                    selection,
                                    selectionArgs,
                                    null,
                                    null,
                                    sortOrder);
                } catch (SQLiteException e) {
                    return null;
                }

                if (cursor == null) {
                    return null;
                }

                cursor.setNotificationUri(context.getContentResolver(), uri);
                return cursor;
            case CODE_SINGLE_FLIGHT:
                final String normalizedPathSegment = uri.getLastPathSegment();
                final String[] selectionArguments = new String[]{normalizedPathSegment};
                try {
                    cursor = databaseHelper.getReadableDatabase().query(FlightContract.FlightEntity.TBL_FLIGHT,
                            projection,
                            FlightContract.FlightEntity._ID + " = ?",
                            selectionArguments,
                            null,
                            null,
                            sortOrder);
                } catch (SQLiteException e) {
                    return null;
                }
                return cursor;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public void shutdown() {
        databaseHelper = null;
        super.shutdown();
    }
}
