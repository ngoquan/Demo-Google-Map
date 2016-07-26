package com.example.ngoquan.demomap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ManageActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnGettingGoogleMap, btnPlacePicker, btnMapDistance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        btnGettingGoogleMap = (Button) findViewById(R.id.btn_getting_start);
        btnPlacePicker = (Button) findViewById(R.id.btn_place_picker);
        btnMapDistance = (Button) findViewById(R.id.btn_map_distance);


        btnGettingGoogleMap.setOnClickListener(this);
        btnPlacePicker.setOnClickListener(this);
        btnMapDistance.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_getting_start:
                intent = new Intent(ManageActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_place_picker:
                intent = new Intent(ManageActivity.this, PlacePickerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_map_distance:
                intent = new Intent(ManageActivity.this, MapDistanceActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
