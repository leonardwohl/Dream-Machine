package com.lenwohl.dreammachine.desktop;

import com.badlogic.gdx.math.Vector2;
import com.lenwohl.dreammachine.gps.AbstractGPSInterface;

public class DesktopGPSEmulator extends AbstractGPSInterface {
	
	private Vector2 virtualGPSPosition = new Vector2(0.0f, 0.0f);
	
	@Override
	public Vector2 getCurrentGPSPosition() {
		return virtualGPSPosition;
	}
	
	public void setVirtualGPSPosition(float latitude, float longitude) {
		virtualGPSPosition.x = latitude;
		virtualGPSPosition.y = longitude;
	}
	
}
