package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.graphics.Texture;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.RenderingManager;

// TODO: Add control over the positions of child components within the window
// TODO: Add proper nine-patch texture tiling to windows

// Simple component with a texture, meant for holding other components
public class Window extends GUIComponent {
	
	public Texture texture;
	public boolean blocksTouch;	// Determines whether or not the window can be clicked through
	
	public Window(float relativeX, float relativeY, float width, float height, Texture texture) {
		super(relativeX, relativeY, width, height);
		this.texture = texture;
		this.blocksTouch = true;
	}
	
	@Override
	public void update() {
		updateChildComponents();
	}
	
	@Override
	public void render() {
		updateScreenCoordinates();
		RenderingManager.getSpriteBatch().draw(texture, screenX, screenY, width, height);
		renderChildComponents();
	}
	
	@Override
	protected void interceptInputEvent(InputEvent event) {
	}
	
	@Override
	protected void handleInputEvent(InputEvent event) {
		// If the window cannot be touched through, block any touch within its bounds
		if (event.type == InputEvent.Type.TOUCH_DOWN && blocksTouch && isPointWithinBounds(event.x, event.y)) {
			event.handled = true;
		}
	}
	
	public boolean getBlocksTouch() {
		return blocksTouch;
	}
	
	public void setBlocksTouch(boolean blocksTouch) {
		this.blocksTouch = blocksTouch;
	}
}
