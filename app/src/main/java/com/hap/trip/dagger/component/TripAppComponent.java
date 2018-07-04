package com.hap.trip.dagger.component;

import com.hap.trip.dagger.module.ContextModule;
import com.hap.trip.dagger.module.NetworkModule;
import com.hap.trip.dagger.scope.ApplicationScope;

import dagger.Component;

/**
 * Created by luis on 6/16/18.
 */
@ApplicationScope
@Component(modules = {NetworkModule.class, ContextModule.class})
public interface TripAppComponent extends AppGraph {
}