package com.example.ngoquan.demomap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

public class PlacePickerActivity extends AppCompatActivity {

    AutoCompleteTextView myLocation;
    Button btnPicker;

    private PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
    private static final int PLACE_PICKER_FLAG = 1;
    protected GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .build();

        myLocation = (AutoCompleteTextView) findViewById(R.id.myLocation);
        btnPicker = (Button) findViewById(R.id.pickerBtn);
        builder = new PlacePicker.IntentBuilder();
        btnPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
//                    builder = new PlacePicker.IntentBuilder();
//                    Intent intent = builder.build(PlacePickerActivity.this);
//                    // Start the Intent by requesting a result, identified by a request code.
//                    startActivityForResult(intent, PLACE_PICKER_FLAG);
                    startActivityForResult(builder.build(PlacePickerActivity.this), PLACE_PICKER_FLAG);
                } catch (GooglePlayServicesRepairableException e) {
                    GooglePlayServicesUtil
                            .getErrorDialog(e.getConnectionStatusCode(), PlacePickerActivity.this, 0);
                } catch (GooglePlayServicesNotAvailableException e) {
                    Toast.makeText(PlacePickerActivity.this, "Google Play Services is not available.",
                            Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PLACE_PICKER_FLAG) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);
                myLocation.setText(place.getName() + ", " + place.getAddress());
            } else if(resultCode == PlacePicker.RESULT_ERROR) {
                Status status = PlacePicker.getStatus(this, data);
                Toast.makeText(this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
        }

//        if (resultCode == RESULT_OK) {
//            switch (requestCode) {
//                case PLACE_PICKER_FLAG:
//
//                    break;
//            }
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
}
