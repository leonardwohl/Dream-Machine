package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.graphics.Texture;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.RenderingManager;

// TODO: Add control over the positions of child components within the window

// Simple component with a texture, meant for holding other components
public class Window extends GUIComponent {
	
	public Texture texture;
	
	public Window(float relativeX, float relativeY, float width, float height, Texture texture) {
		super(relativeX, relativeY, width, height);
		this.texture = texture;
	}
	
	@Override
	public void update() {
		updateScreenCoordinates();
		updateChildComponents();
	}
	
	@Override
	public void render() {
		RenderingManager.getSpriteBatch().draw(texture, screenX, screenY, width, height);
		renderChildComponents();
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
	}
	
}
