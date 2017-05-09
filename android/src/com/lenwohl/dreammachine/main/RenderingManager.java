package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderingManager {
	
	private static SpriteBatch spriteBatch;
	
	public static void initialize() {
		spriteBatch = new SpriteBatch();
	}
	
	public static SpriteBatch getSpriteBatch() {
		return spriteBatch;
	}
	
}
