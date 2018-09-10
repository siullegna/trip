package com.hap.trip.dagger.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.hap.trip.dagger.scope.ApplicationScope;
import com.hap.trip.network.api.TripApi;
import com.hap.trip.network.service.TripRestServiceImpl;
import com.hap.trip.util.TripSettings;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by luis on 6/16/18.
 */
@Module
public class NetworkModule {
    private <T> T getApiAdapter(final OkHttpClient okHttpClient, final Class<T> clazz) {
        return new Retrofit.Builder()
                .baseUrl(TripSettings.getBaseUrl())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(clazz);
    }

    @Provides
    @ApplicationScope
    protected OkHttpClient provideOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addNetworkInterceptor(new StethoInterceptor());
        return builder.build();
    }

    @Provides
    @ApplicationScope
    protected TripRestServiceImpl provideTripRestServiceImpl(final OkHttpClient okHttpClient) {
        final TripApi tripApi = getApiAdapter(okHttpClient, TripApi.class);
        return new TripRestServiceImpl(tripApi);
    }
}