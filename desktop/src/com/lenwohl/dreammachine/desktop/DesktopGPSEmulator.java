package com.lenwohl.dreammachine.desktop;

import com.badlogic.gdx.math.Vector2;
import com.lenwohl.dreammachine.main.AbstractGPSInterface;

public class DesktopGPSEmulator extends AbstractGPSInterface {

    private boolean gpsEnabled;
	private Vector2 virtualGPSPosition = new Vector2(0.0f, 0.0f);

    @Override
    public void setGpsEnabled(boolean b) {
        gpsEnabled = b;
    }

    @Override
	public Vector2 getCurrentGPSPosition() {
		return virtualGPSPosition;
	}
	
	public void setVirtualGPSPosition(float latitude, float longitude) {
		virtualGPSPosition.x = latitude;
		virtualGPSPosition.y = longitude;
	}
	
}
