package com.hap.trip.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.adapter.MyFlightsAdapter;
import com.hap.trip.persistence.room.entity.FlightEntity;
import com.hap.trip.ui.base.BaseAppActivity;
import com.hap.trip.util.IntentFactory;
import com.hap.trip.widget.EmptyScreenView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MyFlightsActivity extends BaseAppActivity implements MyFlightsAdapter.OnFlightClickListener {
    @BindView(R.id.rv_flights)
    RecyclerView rvFlights;
    @BindView(R.id.empty_screen)
    EmptyScreenView emptyScreen;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MyFlightsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_flights);
        super.onCreate(savedInstanceState);

        adapter = new MyFlightsAdapter(this);
        rvFlights.setHasFixedSize(false);
        rvFlights.setLayoutManager(new LinearLayoutManager(this));
        rvFlights.setAdapter(adapter);

        compositeDisposable.add(Observable.just(1)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        final List<FlightEntity> flightEntityList = tripViewModel.selectAllflights();
                        if (flightEntityList.isEmpty()) {
                            rvFlights.setVisibility(View.GONE);
                            emptyScreen.setVisibility(View.VISIBLE);
                        } else {
                            rvFlights.setVisibility(View.VISIBLE);
                            emptyScreen.setVisibility(View.GONE);
                            adapter.addAll(flightEntityList);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    @Override
    public void onClick(FlightEntity flightEntity) {
        final Intent intent = IntentFactory.getFlightDetailIntent(flightEntity, true);
        startActivity(intent);
    }
}
