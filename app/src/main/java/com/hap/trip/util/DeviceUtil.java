package com.hap.trip.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import javax.annotation.Nonnull;

/**
 * Created by luis on 6/23/18.
 */

public class DeviceUtil {
    public static void hideKeyboard(@Nonnull final View view, final Context context) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void showKeyboard(@Nonnull final View view, final Context context) {
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }
}
