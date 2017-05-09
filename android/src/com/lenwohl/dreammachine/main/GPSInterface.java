package com.lenwohl.dreammachine.main;


import android.location.Location;
import com.badlogic.gdx.math.Vector2;

public class GPSInterface {
    
    protected boolean gpsEnabled;
    protected Vector2 virtualGPSPosition = new Vector2(0.0f, 0.0f);
    private Location mLastLocation;
    private Vector2 currentLocation;
    private boolean gpsPermissionGranted;

    public GPSInterface() {

        currentLocation = new Vector2();
        if (mLastLocation != null) {
            currentLocation.set((float)mLastLocation.getLatitude(), (float)mLastLocation.getLongitude());
        }else{
            currentLocation.set(0.0f, 0.0f);
        }

    }
    
	public Vector2 getCurrentGPSPosition() {

        if(gpsPermissionGranted && gpsEnabled){
            if (mLastLocation != null) {
                currentLocation.set((float)mLastLocation.getLatitude(), (float)mLastLocation.getLongitude());
            }
            return currentLocation;
        } else {
            return virtualGPSPosition;
        }
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
    
    public void setGpsEnabled(boolean b){
        if(gpsPermissionGranted) {
            gpsEnabled = b;
        }else{
            gpsEnabled = false;
        }
    }
    
    public void setVirtualGPSPosition(float latitude, float longitude) {
        virtualGPSPosition.x = latitude;
        virtualGPSPosition.y = longitude;
    }
}
