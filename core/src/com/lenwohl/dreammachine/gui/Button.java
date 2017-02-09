package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.RenderingManager;

// TODO: Add animations to buttons

// Simple component with a texture and a pointer to a listener. When the screen is clicked within the bounds of the button,
// The onClick of the listener is called.
public class Button extends GUIComponent {
	
	private TextureRegion texture;
	private Listener listener;
	
	public Button(String id, TextureRegion texture, Listener listener) {
		this(id, 0, 0, 50, 30, texture, listener);
	}
	
	public Button(String id, float width, float height, TextureRegion texture, Listener listener) {
		this(id, 0, 0, width, height, texture, listener);
	}
	
	public Button(String id, float relativeX, float relativeY, float width, float height, TextureRegion texture, Listener listener) {
		super(id, relativeX, relativeY, width, height);
		this.texture = texture;
		this.listener = listener;
	}
	
	@Override
	public void update() {
		updateChildComponents();
	}
	
	@Override
	public void render() {
		RenderingManager.getSpriteBatch().draw(texture, screenX, screenY, width, height);
		renderChildComponents();
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
		if (event.handled) return;
		for (int i = childComponents.size()-1; i >= 0; i--) {
			childComponents.get(i).processInputEvent(event);
		}
		// Check for click
		if (event.type == InputEvent.Type.TOUCH_DOWN && isPointWithinBounds(event.x, event.y)) {
			listener.onClick();
			event.handled = true;
		}
	}
	
	// Pass an implementation to the button to define an on-click action
	public interface Listener {
		void onClick();
	}
	
}
