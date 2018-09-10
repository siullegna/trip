package com.hap.trip.dagger.module;

import com.hap.trip.dagger.scope.ApplicationScope;
import com.hap.trip.persistence.room.source.FlightQueryDataSourceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {
    @Provides
    @ApplicationScope
    protected FlightQueryDataSourceImpl provideFlightQueryData() {
        return new FlightQueryDataSourceImpl();
    }
}
