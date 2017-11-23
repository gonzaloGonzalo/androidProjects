package com.example.admin.myposition;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends Activity implements LocationListener {

    private static final String TAG = LocationActivity.class.getSimpleName();
    private TextView latituteField;
    private TextView longitudeField;
    private TextView altitudeField;
    private LocationManager locationManager;
    private String provider;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        latituteField = findViewById(R.id.TextView02);
        longitudeField = findViewById(R.id.TextView04);
        altitudeField = findViewById(R.id.TextView06);

        try{
            LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
            boolean enabled = service
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // check if enabled and if not send user to the GSP settings
            // Better solution would be to display a dialog and suggesting to
            // go to the settings
            if (!enabled) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

            // Get the location manager
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            // Define the criteria how to select the locatioin provider -> use
            // default
            Criteria criteria = new Criteria();
            criteria.setPowerRequirement(Criteria.POWER_LOW); // Chose your desired power consumption level.
            criteria.setAccuracy(Criteria.ACCURACY_FINE); // Choose your accuracy requirement.
            criteria.setSpeedRequired(true); // Chose if speed for first location fix is required.
            criteria.setAltitudeRequired(true); // Choose if you use altitude.
            criteria.setBearingRequired(false); // Choose if you use bearing.
            criteria.setCostAllowed(false); // Choose if this provider can waste money :-)
            provider = locationManager.getBestProvider(criteria, true);
            Location location = locationManager.getLastKnownLocation(provider);

            // Initialize the location fields
            if (location != null) {
                System.out.println("Provider " + provider + " has been selected.");
                onLocationChanged(location);
            } else {
                latituteField.setText("Location not available");
                longitudeField.setText("Location not available");
                altitudeField.setText("Location not available");
            }
        }
        catch (SecurityException e){
            Log.d(TAG, e.getMessage());
        }

    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        try{
            locationManager.requestLocationUpdates(provider, 400, 1, this);
        }
        catch (SecurityException e){
            Log.d(TAG, e.getMessage());
        }
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {

        float lat = (float) location.getLatitude();
        float lng = (float) location.getLongitude();
        float alt = (float) location.getAltitude();
        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
        altitudeField.setText(String.valueOf(alt));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }
}
