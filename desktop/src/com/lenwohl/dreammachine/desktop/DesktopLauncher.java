package com.lenwohl.dreammachine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lenwohl.dreammachine.main.DreamMachine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DreamMachine.WIDTH;
		config.height = DreamMachine.HEIGHT;
		
		DreamMachine dreamMachine = new DreamMachine();
		dreamMachine.gpsInterface = new DesktopGPSEmulator();
				
		new LwjglApplication(dreamMachine, config);
	}
}
