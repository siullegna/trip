package com.hap.trip.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/27/18.
 */

public class PassengerPickerViewGroup extends LinearLayout {
    @BindView(R.id.passenger)
    AppCompatTextView passenger;
    @BindView(R.id.age)
    AppCompatTextView age;
    @BindView(R.id.passenger_picker_view)
    PassengerPickerView passengerPickerView;

    private String passengerValue;
    private String ageValue;

    public PassengerPickerViewGroup(Context context) {
        this(context, null);
    }

    public PassengerPickerViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassengerPickerViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.passenger_picker_view_group, this);
        ButterKnife.bind(this, view);

        final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PassengerPickerViewGroupAttrs, 0, 0);
        if (typedArray != null) {
            setupAttrs(typedArray);
            setupValues();
        }
    }

    private void setupAttrs(@Nonnull final TypedArray typedArray) {
        passengerValue = typedArray.getString(R.styleable.PassengerPickerViewGroupAttrs_passengerType);
        ageValue = typedArray.getString(R.styleable.PassengerPickerViewGroupAttrs_ageRange);
    }

    private void setupValues() {
        passenger.setText(passengerValue);
        age.setText(ageValue);
    }

    public void setCount(final int count, @Nonnull PassengerPickerView.PickerCountListener pickerCountListener) {
        passengerPickerView.setupPicker(count, pickerCountListener);
    }

    public int getCount() {
        return passengerPickerView.getCount();
    }
}
