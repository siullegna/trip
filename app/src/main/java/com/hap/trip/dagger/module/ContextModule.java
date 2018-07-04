package com.hap.trip.dagger.module;

import android.content.Context;
import android.content.res.Resources;

import com.hap.trip.dagger.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luis on 6/16/18.
 */
@Module
public class ContextModule {
    private final Context context;
    private final Resources resources;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
        this.resources = context.getResources();
    }

    @Provides
    @ApplicationScope
    protected Context provideContext() {
        return context;
    }

    @Provides
    @ApplicationScope
    protected Resources provideResources() {
        return resources;
    }
}
