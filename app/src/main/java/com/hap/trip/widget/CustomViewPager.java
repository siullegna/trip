package com.hap.trip.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by luis on 6/23/18.
 * <p>
 * Since I don't need the swipe to change the tabs, I am creating this custom Pager to disable that.
 */

public class CustomViewPager extends ViewPager {
    public CustomViewPager(@NonNull Context context) {
        this(context, null);
    }

    public CustomViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}