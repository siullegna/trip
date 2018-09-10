package com.hap.trip.ui.flight;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.hap.trip.R;
import com.hap.trip.model.flight.FlightItemResponse;
import com.hap.trip.model.location.LocationAirportItem;
import com.hap.trip.model.search.SearchFlightItem;
import com.hap.trip.ui.airport.BaseAirportActivity;
import com.hap.trip.ui.base.BaseAppFragment;
import com.hap.trip.ui.date.DatePickerActivity;
import com.hap.trip.ui.passenger.SelectPassengerActivity;
import com.hap.trip.util.FlightUtil;
import com.hap.trip.util.IntentFactory;
import com.hap.trip.widget.InputTextView;

import static android.app.Activity.RESULT_OK;

/**
 * Created by luis on 6/23/18.
 */
public abstract class BaseTripFragment extends BaseAppFragment {
    private static final int REQUEST_ORIGIN_KEY = 1001;
    private static final int REQUEST_DESTINATION_KEY = 1002;
    static final int REQUEST_DEPART_KEY = 1003;
    private static final int REQUEST_PASSENGERS_KEY = 1004;

    protected InputTextView inputOrigin;
    protected InputTextView inputDestination;
    protected InputTextView inputDepart;
    protected InputTextView inputPassengers;
    protected AppCompatButton btnContinue;

    protected LocationAirportItem origin;
    protected LocationAirportItem destination;

    protected OnSearchFlightEvent onSearchFlightEvent;

    protected boolean isValid() {
        boolean isValid = true;

        if (!inputOrigin.isValidInput()) {
            isValid = false;
            inputOrigin.setError(getString(R.string.flight_origin_error));
        }

        if (!inputDestination.isValidInput()) {
            isValid = false;
            inputDestination.setError(getString(R.string.flight_destination_error));
        }

        if (!inputDepart.isValidInput()) {
            isValid = false;
            inputDepart.setError(getString(R.string.flight_depart_error));
        }

        if (!inputPassengers.isValidInput()) {
            isValid = false;
            inputPassengers.setError(getString(R.string.flight_passenger_error));
        }

        return isValid;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnSearchFlightEvent) {
            onSearchFlightEvent = (OnSearchFlightEvent) context;
        } else {
            throw new RuntimeException("Parent activity must implement {OnSearchFlightEvent}");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ORIGIN_KEY:
                if (resultCode == RESULT_OK) {
                    origin = data.getParcelableExtra(BaseAirportActivity.ARG_SELECTED_FLIGHT_KEY);
                    inputOrigin.resetValues();
                    inputOrigin.setText(getString(R.string.flight_format, origin.getAirportName(), origin.getAirportCode()));
                }
                break;
            case REQUEST_DESTINATION_KEY:
                if (resultCode == RESULT_OK) {
                    destination = data.getParcelableExtra(BaseAirportActivity.ARG_SELECTED_FLIGHT_KEY);
                    inputDestination.resetValues();
                    inputDestination.setText(getString(R.string.flight_format, destination.getAirportName(), destination.getAirportCode()));
                }
                break;
            case REQUEST_DEPART_KEY:
                if (resultCode == RESULT_OK) {
                    final long date = data.getLongExtra(DatePickerActivity.RESULT_SELECTED_DATE_KEY, System.currentTimeMillis());
                    inputDepart.resetValues();
                    inputDepart.setDate(date);
                }
                break;
            case REQUEST_PASSENGERS_KEY:
                if (resultCode == RESULT_OK) {
                    final int adultCount = data.getIntExtra(SelectPassengerActivity.ARG_ADULT_KEY, 1);
                    final int childrenCount = data.getIntExtra(SelectPassengerActivity.ARG_CHILDREN_KEY, 1);
                    final int infantCount = data.getIntExtra(SelectPassengerActivity.ARG_INFANT_KEY, 1);

                    inputPassengers.setPassengers(adultCount, childrenCount, infantCount);
                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected void setupViews(final View view) {
        inputOrigin = view.findViewById(R.id.input_origin);
        inputOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent airportSearchIntent = IntentFactory.getOriginSearchIntent(origin);
                startActivityForResult(airportSearchIntent, REQUEST_ORIGIN_KEY);
            }
        });

        inputDestination = view.findViewById(R.id.input_destination);
        inputDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent airportSearchIntent = IntentFactory.getDestinationSearchIntent(destination);
                startActivityForResult(airportSearchIntent, REQUEST_DESTINATION_KEY);
            }
        });

        inputDepart = view.findViewById(R.id.input_depart);
        inputDepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent departIntent = IntentFactory.getDateIntent(System.currentTimeMillis());
                startActivityForResult(departIntent, REQUEST_DEPART_KEY);
            }
        });

        inputPassengers = view.findViewById(R.id.input_passengers);
        inputPassengers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent passengerIntent = IntentFactory
                        .getPassengerIntent(inputPassengers.getAdultCount(), inputPassengers.getChildrenCount(), inputPassengers.getInfantCount());
                startActivityForResult(passengerIntent, REQUEST_PASSENGERS_KEY);
            }
        });

        btnContinue = view.findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    searchFlights();
                }
            }
        });
    }

    private void searchFlights() {
        if (onSearchFlightEvent != null) {
            onSearchFlightEvent.showLoader();
        }
        final SearchFlightItem searchFlightItem = new SearchFlightItem(origin.getAirportName(), origin.getAirportCode(), destination.getAirportName(), destination.getAirportCode(), inputDepart.getDate());
        searchFlightItem.setReturnDate(getReturningDate());
        searchFlightItem.setAdults(inputPassengers.getAdultCount());
        searchFlightItem.setChildren(inputPassengers.getChildrenCount());
        searchFlightItem.setInfants(inputPassengers.getInfantCount());
        searchFlightItem.setNonstops(false);
        searchFlightItem.setMaxPrice(-1);
        searchFlightItem.setTravelClass(null);

        tripViewModel.searchFlights(searchFlightItem)
                .observe(this, new Observer<FlightItemResponse>() {
                    @Override
                    public void onChanged(@Nullable FlightItemResponse flightItemResponse) {
                        if (onSearchFlightEvent != null) {
                            onSearchFlightEvent.hideLoader();
                        }
                        if (flightItemResponse == null) {
                            if (onSearchFlightEvent != null) {
                                onSearchFlightEvent.showError();
                            }
                            return;
                        }

                        if (flightItemResponse.getError() != null || flightItemResponse.getFlightItem() == null || flightItemResponse.getFlightItem().getResults() == null) {
                            if (onSearchFlightEvent != null) {
                                onSearchFlightEvent.showError();
                            }
                        } else {
                            final Intent intent = IntentFactory.getFlightResultIntent(flightItemResponse.getFlightItem(), searchFlightItem);
                            startActivity(intent);
                            resetValues();
                        }
                    }
                });
    }

    protected void resetValues() {
        inputOrigin.resetValues();
        inputDestination.resetValues();
        inputDepart.resetValues();
        inputPassengers.resetValues();
    }

    public abstract Long getReturningDate();

    public interface OnSearchFlightEvent {
        void showLoader();

        void hideLoader();

        void showError();
    }
}
