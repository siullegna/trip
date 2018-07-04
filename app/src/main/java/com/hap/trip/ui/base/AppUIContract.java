package com.hap.trip.ui.base;

import android.support.design.widget.BaseTransientBottomBar;

/**
 * Created by luis on 6/18/18.
 */

interface AppUIContract {
    void showSnackBar(final String message, @BaseTransientBottomBar.Duration int duration);
}
