package com.hap.trip.ui.airport;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hap.trip.R;
import com.hap.trip.TripApplication;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.model.location.LocationAirportItemResponse;
import com.hap.trip.util.PermissionHelper;
import com.hap.trip.widget.EmptyScreenView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by luis on 6/23/18.
 */

public class AirportMapSearchFragment extends BaseAirportFragment implements GoogleMap.OnMarkerClickListener {
    private static final int REQUEST_CODE_LOCATION = 1001;

    public static AirportMapSearchFragment getInstance() {
        return new AirportMapSearchFragment();
    }

    @BindView(R.id.map_view)
    MapView mapView;
    @BindView(R.id.empty_screen)
    EmptyScreenView emptyScreenView;
    @BindView(R.id.loader)
    ProgressBar loader;

    private GoogleMap googleMap;
    private LocationManager locationManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_airport_map_search, container, false);
        ButterKnife.bind(this, view);

        mapView.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);

        mapView.onCreate(savedInstanceState);
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        try {
            MapsInitializer.initialize(TripApplication.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!PermissionHelper.isLocationPermission()) {
            mapView.setVisibility(View.GONE);
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE_LOCATION);
        } else {
            setupMapView();
            hideEmptyScreen();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupMapView();
                hideEmptyScreen();
            } else {
                mapView.setVisibility(View.GONE);
                showEmptyScreen();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void showEmptyScreen() {
        emptyScreenView.setInfoHeader(R.string.search_airport_error_header);
        emptyScreenView.setInfoSubHeader(R.string.search_airport_error_info);
        emptyScreenView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyScreen() {
        emptyScreenView.setVisibility(View.GONE);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.getTag() != null && marker.getTag() instanceof LocationAirportItem) {
            final LocationAirportItem locationAirportItem = (LocationAirportItem) marker.getTag();
            onAirportListener.onOriginSelected(locationAirportItem);
            return true;
        }
        return false;
    }

    private void setupMapView() {
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                if (PermissionHelper.isLocationPermission()) {
                    googleMap = mMap;
                    googleMap.setOnMarkerClickListener(AirportMapSearchFragment.this);

                    // For showing a move to my location button
                    googleMap.setMyLocationEnabled(true);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, mLocationListener);
                } else {
                    showEmptyScreen();
                    mapView.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showAirportByLocation(final double latitude, final double longitude) {
        tripViewModel.searchAirportByLocation(latitude, longitude).observe(this, new Observer<LocationAirportItemResponse>() {
            @Override
            public void onChanged(@Nullable LocationAirportItemResponse locationAirportItemResponse) {
                if (locationAirportItemResponse == null) {
                    return;
                }
                if (locationAirportItemResponse.getError() != null) {
                    mapView.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                    onAirportListener.onAirportLoadeError();
                } else if (locationAirportItemResponse.getAirportItemArrayList() != null) {
                    mapView.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                    for (LocationAirportItem locationAirportItem : locationAirportItemResponse.getAirportItemArrayList()) {
                        final LatLng airportLocation = new LatLng(locationAirportItem.getLocation().getLatitude(), locationAirportItem.getLocation().getLongitude());
                        final Marker marker = googleMap.addMarker(new MarkerOptions()
                                .position(airportLocation)
                                .title(locationAirportItem.getAirportCode())
                                .snippet(locationAirportItem.getAirportName())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_plane_depart)));
                        marker.setTag(locationAirportItem);
                    }
                }
            }
        });
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            // For dropping a marker at a point on the Map
            final LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            showAirportByLocation(location.getLatitude(), location.getLongitude());
            // For zooming automatically to the location of the marker
            final CameraPosition cameraPosition = new CameraPosition.Builder().target(currentLocation).zoom(9).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(getContext(), "GPS is Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(getContext(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
        }
    };
}
