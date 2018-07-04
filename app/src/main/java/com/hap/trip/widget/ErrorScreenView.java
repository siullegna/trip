package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hap.trip.R;

/**
 * Created by luis on 6/18/18.
 */

public class ErrorScreenView extends LinearLayout {
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
        inflater.inflate(R.layout.error_screen_view, this);

        final PhotoDraweeView photoDraweeView = findViewById(R.id.error_icon);

        photoDraweeView.setupImage(R.drawable.ic_could_error);
    }
}
