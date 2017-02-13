package com.lenwohl.dreammachine;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;

import com.badlogic.gdx.math.Vector2;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import com.lenwohl.dreammachine.main.AbstractGPSInterface;

public class AndroidGPSInterface extends AbstractGPSInterface {

    private Location mLastLocation;
    private Vector2 currentLocation;
    private boolean gpsPermissionGranted;

    public AndroidGPSInterface() {

        currentLocation = new Vector2();
        if (mLastLocation != null) {
            currentLocation.set((float)mLastLocation.getLatitude(), (float)mLastLocation.getLongitude());
        }else{
            currentLocation.set(0.0f, 0.0f);
        }

    }

	@Override
	public Vector2 getCurrentGPSPosition() {

        if(gpsPermissionGranted && gpsEnabled){
            if (mLastLocation != null) {
                currentLocation.set((float)mLastLocation.getLatitude(), (float)mLastLocation.getLongitude());
            }
            return currentLocation;
        }
		return null;
	}

    public void updateLocation(Location location){
        if(location != null){
            mLastLocation = location;
            currentLocation.set((float)mLastLocation.getLatitude(), (float)mLastLocation.getLongitude());
        }
    }

    public void setPermission(boolean permission){
        gpsPermissionGranted = permission;
    }

    @Override
    public void setGpsEnabled(boolean b){
        if(gpsPermissionGranted) {
            gpsEnabled = b;
        }else{
            gpsEnabled = false;
        }
    }
	
}
