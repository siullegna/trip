package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.hap.trip.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by luis on 6/18/18.
 */

public class ErrorScreenView extends LinearLayout {
    @BindView(R.id.error_header)
    AppCompatTextView errorHeader;
    @BindView(R.id.error_subheader)
    AppCompatTextView errorSubHeader;

    public ErrorScreenView(Context context) {
        this(context, null);
    }

    public ErrorScreenView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorScreenView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater == null) {
            return;
        }
        final View view = inflater.inflate(R.layout.error_screen_view, this);
        ButterKnife.bind(this, view);
    }

    public void setInfoHeader(final String header) {
        errorHeader.setText(header);
    }

    public void setInfoHeader(final int headerId) {
        errorHeader.setText(headerId);
    }

    public void setInfoSubHeader(final String subHeader) {
        errorSubHeader.setText(subHeader);
    }

    public void setInfoSubHeader(final int subHeaderId) {
        errorSubHeader.setText(subHeaderId);
    }
}
