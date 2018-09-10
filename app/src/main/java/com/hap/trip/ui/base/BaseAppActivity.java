package com.hap.trip.ui.base;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.network.service.TripRestServiceImpl;
import com.hap.trip.viewmodel.TripViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/18/18.
 */

public abstract class BaseAppActivity extends AppCompatActivity implements AppUIContract {
    private Snackbar snackbar;
    @Nullable
    @BindView(R.id.root)
    CoordinatorLayout root;
    @BindView(R.id.toolbar)
    @Nullable
    protected Toolbar toolbar;

    protected TripViewModel tripViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showSnackBar(String message, int duration) {
        if (root == null) {
            return;
        }

        if (snackbar != null) {
            snackbar.dismiss();
        }

        snackbar = Snackbar.make(root, message, duration);

        snackbar.show();
    }

    protected void setResult(final Intent result) {
        setResult(RESULT_OK, result);
        finish();
    }

    protected void showHomeButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
    }
}