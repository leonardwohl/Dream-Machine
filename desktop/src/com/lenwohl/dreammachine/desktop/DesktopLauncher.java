package com.lenwohl.dreammachine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.lenwohl.dreammachine.DreamMachine;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = DreamMachine.WIDTH;
		config.height = DreamMachine.HEIGHT;
		new LwjglApplication(new DreamMachine(), config);
	}
}
