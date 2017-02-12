package com.lenwohl.dreammachine.gui;

import com.lenwohl.dreammachine.input.InputEvent;

// TODO: Add control over the positions of child components within the window


// Simple component with a texture, meant for holding other components
public class Window extends GUIComponent {
	
	private NinePatch ninePatch;
	private boolean blocksTouch;	// Determines whether or not the window can be clicked through
	
	public Window(String id, NinePatch ninePatch) {
		this(id, 0, 0, 200, 100, ninePatch);
	}
	
	public Window(String id, float width, float height, NinePatch ninePatch) {
		this(id, 0, 0, width, height, ninePatch);
	}
	
	public Window(String id, float relativeX, float relativeY, float width, float height, NinePatch ninePatch) {
		super(id, relativeX, relativeY, width, height);
		this.ninePatch = ninePatch;
		this.blocksTouch = true;
	}
	
	@Override
	public void update() {
		updateChildComponents();
	}
	
	@Override
	public void render() {
		ninePatch.render(screenX, screenY, width, height);
		renderChildComponents();
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
		if (event.handled) return;
		for (int i = childComponents.size()-1; i >= 0; i--) {
			childComponents.get(i).processInputEvent(event);
		}
		// If touch is within bounds of window and window cannot be touched through, block the event
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
