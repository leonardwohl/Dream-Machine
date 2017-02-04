package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.lenwohl.dreammachine.rendering.RenderingManager;

// Simple component with a texture and a pointer to a listener. When the screen is clicked within the bounds of the button,
// The onClick of the listener is called.
// Currently doesn't check if button is behind other buttons.
// TODO: Perhaps the parent or some sort of GUI manager can pass input down to the button, instead of the button checking directly?
public class Button extends GUIComponent {
	
	private Texture texture;
	private Listener listener;
	
	public Button(float relativeX, float relativeY, float width, float height, Texture texture, Listener listener) {
		super(relativeX, relativeY, width, height);
		this.texture = texture;
		this.listener = listener;
	}
	
	@Override
	public void update() {
		updateScreenCoordinates();
		// Check if clicked
		if (Gdx.input.justTouched()) {
			float touchX = Gdx.input.getX();
			float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();	// Invert because input coords start at the top-left corner
			if ( touchX>screenX && touchX<screenX+width && touchY>screenY && touchY<screenY+height) {
				listener.onClick();
			}
		}
	}
	
	@Override
	public void render() {
		RenderingManager.getSpriteBatch().draw(texture, screenX, screenY, width, height);
	}
	
	// Pass an implementation to the button to define an on-click action
	public interface Listener {
		void onClick();
	}
	
}
