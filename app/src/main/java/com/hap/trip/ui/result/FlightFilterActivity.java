package com.hap.trip.ui.result;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.hap.trip.R;
import com.hap.trip.model.filter.FilterItem;
import com.hap.trip.ui.base.BaseAppActivity;

import butterknife.BindView;

public class FlightFilterActivity extends BaseAppActivity {
    public static final String ARG_FILTER_ITEM_KEY = "com.hap.trip.ui.result.ARG_FILTER_ITEM_KEY";
    private static final int SB_DURATION_OFFSET = 5;
    private static final int SB_DURATION_MAX = 24;

    @BindView(R.id.s_sort_type)
    AppCompatSpinner sSortType;
    @BindView(R.id.sb_duration)
    SeekBar sbDuration;
    @BindView(R.id.tv_flight_hours)
    AppCompatTextView tvFlightHours;
    @BindView(R.id.cb_nonstop)
    CheckBox cbNonstop;
    @BindView(R.id.cb_one_stop)
    CheckBox cbOneStop;
    @BindView(R.id.cb_two_plus_stops)
    CheckBox cbTwoPlusStops;

    private FilterItem filterItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_flight_filter);
        super.onCreate(savedInstanceState);

        showHomeButton();

        final Bundle args = getIntent().getExtras();
        if (args != null) {
            filterItem = args.getParcelable(ARG_FILTER_ITEM_KEY);
            if (filterItem == null) {
                filterItem = new FilterItem();  // default values
            }
        } else {
            filterItem = new FilterItem();  // default values
        }

        sSortType.setSelection(filterItem.getSortType().getSortType());

        sbDuration.setProgress(filterItem.getDurationHours() - SB_DURATION_OFFSET);
        if (filterItem.getDurationHours() < SB_DURATION_MAX) {
            tvFlightHours.setText(getString(R.string.flight_filter_duration, filterItem.getDurationHours()));
        } else {
            tvFlightHours.setText(getString(R.string.flight_filter_duration_all, filterItem.getDurationHours()));
        }
        cbNonstop.setChecked(filterItem.isNonStop());
        cbOneStop.setChecked(filterItem.isOneStop());
        cbTwoPlusStops.setChecked(filterItem.isTwoPlusStops());

        sSortType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterItem.setSortType(FilterItem.SortType.fromType(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sbDuration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                filterItem.setDurationHours(seekBar.getProgress() + SB_DURATION_OFFSET);
                if (filterItem.getDurationHours() < SB_DURATION_MAX) {
                    tvFlightHours.setText(getString(R.string.flight_filter_duration, filterItem.getDurationHours()));
                } else {
                    tvFlightHours.setText(getString(R.string.flight_filter_duration_all, filterItem.getDurationHours()));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        cbNonstop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterItem.setNonStop(isChecked);
                if (isChecked) {
                    filterItem.setOneStop(false);
                    filterItem.setTwoPlusStops(false);
                    cbOneStop.setChecked(filterItem.isOneStop());
                    cbTwoPlusStops.setChecked(filterItem.isTwoPlusStops());
                }
            }
        });

        cbOneStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterItem.setOneStop(isChecked);
                if (isChecked) {
                    filterItem.setNonStop(false);
                    cbNonstop.setChecked(filterItem.isNonStop());
                }
            }
        });

        cbTwoPlusStops.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterItem.setTwoPlusStops(isChecked);
                if (isChecked) {
                    filterItem.setNonStop(false);
                    cbNonstop.setChecked(filterItem.isNonStop());
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(ARG_FILTER_ITEM_KEY, filterItem);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                final Intent result = new Intent();
                result.putExtra(ARG_FILTER_ITEM_KEY, filterItem);
                setResult(result);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
