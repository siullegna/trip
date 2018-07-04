package com.hap.trip.ui.base;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.network.service.TripRestServiceImpl;
import com.hap.trip.viewmodel.TripViewModel;

import javax.inject.Inject;

/**
 * Created by luis on 6/18/18.
 */

public abstract class BaseAppActivity extends AppCompatActivity implements AppUIContract {
    private Snackbar snackbar;
    private CoordinatorLayout root;
    @Nullable
    protected Toolbar toolbar;

    protected TripViewModel tripViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);

        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }

    @Override
    public void showSnackBar(String message, int duration) {
        if (snackbar != null) {
            snackbar.dismiss();
        }

        snackbar = Snackbar.make(root, message, duration);

        snackbar.show();
    }
}