package com.lenwohl.dreammachine.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.lenwohl.dreammachine.rendering.RenderingManager;

// TODO: Build a proper UI system

public class ShittyButton {
	
	private float x;
	private float y;
	private float width;
	private float height;
	private Texture texture;
	private Listener listener;
	
	public ShittyButton(float x, float y, float width, float height, Texture texture, Listener listener) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.listener = listener;
	}
	
	public void render() {
		RenderingManager.getSpriteBatch().draw(texture, x, y, width, height);
	}
	
	// You click shitty button? Shitty button does thing
	public void update() {
		if (Gdx.input.justTouched()) {
			float touchX = Gdx.input.getX();
			float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();	// Invert because input coords start at the top-left corner
			if ( touchX>x && touchX<x+width && touchY>y && touchY<y+height) {
				listener.onClick();
			}
		}
	}
	
	// Pass an implementation to the button to define an on-click action
	public interface Listener {
		void onClick();
	}
	
}
