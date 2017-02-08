package com.lenwohl.dreammachine.desktop;

import com.badlogic.gdx.math.Vector2;
import com.lenwohl.dreammachine.main.AbstractGPSInterface;

public class DesktopGPSEmulator extends AbstractGPSInterface {

    @Override
	public Vector2 getCurrentGPSPosition() {
		return virtualGPSPosition;
	}
	
}
