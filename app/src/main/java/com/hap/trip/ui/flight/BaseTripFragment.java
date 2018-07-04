package com.hap.trip.ui.flight;

import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.hap.trip.R;
import com.hap.trip.ui.base.BaseAppFragment;
import com.hap.trip.ui.date.DatePickerActivity;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_ORIGIN_KEY:

                break;
            case REQUEST_DESTINATION_KEY:

                break;
            case REQUEST_DEPART_KEY:
                if (resultCode == RESULT_OK) {
                    final long date = data.getLongExtra(DatePickerActivity.RESULT_SELECTED_DATE_KEY, System.currentTimeMillis());
                    inputDepart.resetValues();
                    inputDepart.setDate(date);
                }
                break;
            case REQUEST_PASSENGERS_KEY:

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
                final Intent airportSearchIntent = IntentFactory.getAirportSearchIntent(getString(R.string.flight_origin));
                startActivityForResult(airportSearchIntent, REQUEST_ORIGIN_KEY);
            }
        });

        inputDestination = view.findViewById(R.id.input_destination);
        inputDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent airportSearchIntent = IntentFactory.getAirportSearchIntent(getString(R.string.flight_origin));
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

                }
            }
        });
    }
}
