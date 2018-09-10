package com.hap.trip.persistence.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.hap.trip.TripApplication;
import com.hap.trip.persistence.DatabaseInfo;
import com.hap.trip.persistence.room.converter.DateConverter;
import com.hap.trip.persistence.room.converter.FlightConverter;
import com.hap.trip.persistence.room.dao.FlightDao;
import com.hap.trip.persistence.room.entity.FlightEntity;

@Database(entities = {FlightEntity.class}, version = DatabaseInfo.DB_VERSION, exportSchema = false)
@TypeConverters({FlightConverter.class, DateConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract FlightDao flightDao();

    public static AppDatabase getInstance() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(TripApplication.getInstance().getApplicationContext(), AppDatabase.class, DatabaseInfo.DB_NAME)
                    .build();
        }

        return INSTANCE;
    }
}