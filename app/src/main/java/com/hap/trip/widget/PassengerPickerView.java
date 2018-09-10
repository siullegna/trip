package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
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

public class PassengerPickerView extends LinearLayout {
    private static final int MAX_PASSENGER_COUNT = 8;

    @BindView(R.id.minus)
    AppCompatButton minus;
    @BindView(R.id.count)
    AppCompatTextView count;
    @BindView(R.id.plus)
    AppCompatButton plus;

    private int countValue = 0;

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
        final View view = inflater.inflate(R.layout.passenger_picker_view, this);
        ButterKnife.bind(this, view);
    }

    public int getCount() {
        return countValue;
    }

    public void setupPicker(final int count, @Nonnull final PickerCountListener pickerCountListener) {
        this.countValue = count;
        this.count.setText(String.valueOf(count));

        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countValue > 0) {
                    countValue--;
                    PassengerPickerView.this.count.setText(String.valueOf(countValue));
                    pickerCountListener.onCountChange(countValue);
                }
            }
        });

        plus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (countValue < MAX_PASSENGER_COUNT) {
                    countValue++;
                    PassengerPickerView.this.count.setText(String.valueOf(countValue));
                    pickerCountListener.onCountChange(countValue);
                }
            }
        });
    }

    public interface PickerCountListener {
        void onCountChange(final int countValue);
    }
}
