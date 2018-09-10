package com.hap.trip.persistence.room.source;

import android.database.Cursor;

import com.hap.trip.persistence.room.AppDatabase;
import com.hap.trip.persistence.room.dao.FlightDao;
import com.hap.trip.persistence.room.entity.FlightEntity;

import java.util.ArrayList;
import java.util.List;

public class FlightQueryDataSourceImpl implements FlightDao {
    private final FlightDao flightDao = AppDatabase.getInstance().flightDao();

    @Override
    public void insertFlights(FlightEntity... flightEntities) {
        flightDao.insertFlights(flightEntities);
    }

    @Override
    public void insertFlights(ArrayList<FlightEntity> flightEntities) {
        flightDao.insertFlights(flightEntities);
    }

    @Override
    public void updateFlights(FlightEntity... flightEntities) {
        flightDao.updateFlights(flightEntities);
    }

    @Override
    public void deleteFlights(FlightEntity... flightEntities) {
        flightDao.deleteFlights(flightEntities);
    }

    @Override
    public List<FlightEntity> selectAllList() {
        return flightDao.selectAllList();
    }

    @Override
    public Cursor loadAllCursor() {
        return flightDao.loadAllCursor();
    }
}
