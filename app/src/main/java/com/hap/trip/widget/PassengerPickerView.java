package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hap.trip.R;

/**
 * Created by luis on 6/27/18.
 */

public class PassengerPickerView extends LinearLayout {
    public PassengerPickerView(Context context) {
        this(context, null);
    }

    public PassengerPickerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PassengerPickerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        inflater.inflate(R.layout.passenger_picker_view, this);
    }
}
