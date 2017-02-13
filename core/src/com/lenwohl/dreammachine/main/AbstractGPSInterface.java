package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGPSInterface {

    protected boolean gpsEnabled;
	protected Vector2 virtualGPSPosition = new Vector2(0.0f, 0.0f);
	
	public abstract Vector2 getCurrentGPSPosition();
	
    public void setGpsEnabled(boolean b) {
		gpsEnabled = b;
	}
	
	public void setVirtualGPSPosition(float latitude, float longitude) {
		virtualGPSPosition.x = latitude;
		virtualGPSPosition.y = longitude;
	}
	
}