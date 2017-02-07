package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.math.Vector2;

public abstract class AbstractGPSInterface {

    public boolean gpsEnabled;
    public abstract void setGpsEnabled(boolean b);
	public abstract Vector2 getCurrentGPSPosition();
	
}