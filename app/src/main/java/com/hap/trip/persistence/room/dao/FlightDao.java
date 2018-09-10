package com.hap.trip.persistence.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import com.hap.trip.persistence.FlightContract;
import com.hap.trip.persistence.room.entity.FlightEntity;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface FlightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFlights(final FlightEntity... flightEntities);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFlights(final ArrayList<FlightEntity> flightEntities);

    @Update
    void updateFlights(final FlightEntity... flightEntities);

    @Delete
    void deleteFlights(final FlightEntity... flightEntities);

    @Query("SELECT * FROM " + FlightContract.FlightEntity.TBL_FLIGHT)
    List<FlightEntity> selectAllList();

    @Query("SELECT * FROM " + FlightContract.FlightEntity.TBL_FLIGHT)
    Cursor loadAllCursor();
}
