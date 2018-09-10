package com.hap.trip.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.hap.trip.TripApplication;

public class PermissionHelper {
    private static boolean isPermission(final Context context, final String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static void requestPermission(final Activity activity, final String permission, final int requestCode) {
        ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);

    }

    public static boolean isLocationPermission() {
        return isPermission(TripApplication.getInstance(), Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public static void requestLocationPermission(final Activity activity, final int requestCode) {
        requestPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION, requestCode);
    }
}
