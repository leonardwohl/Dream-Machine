package com.lenwohl.dreammachine.gui;

import com.lenwohl.dreammachine.input.InputEvent;

import java.util.ArrayList;

// GUI is made of up GUI Components. Each GUI Component may have a parent and any number of child components.
// Parents are resposible for calling update() and render() on their children. Children must set their screen coordinates
// relative to those of their parent, if one exists, so that if a parent moves, all children will move accordingly.
// Parents are also responsible for passing input events down to their children. Components can handle and pass down input
// in any order, but in most cases, input should be passed down in reverse child component order (opposite of rendering order)
public abstract class GUIComponent {
	
	protected float relativeX = 0;
	protected float relativeY = 0;
	protected float screenX = 0;
	protected float screenY = 0;
	protected float width = 0;
	protected float height = 0;
	protected ArrayList<GUIComponent> childComponents;
	protected GUIComponent parentComponent;
	
	public GUIComponent(float relativeX, float relativeY, float width, float height) {
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.width = width;
		this.height = height;
		childComponents = new ArrayList<GUIComponent>();
		parentComponent = null;
		updateScreenCoordinates();
	}
	
	public abstract void update();
	public abstract void render();
	public abstract void processInputEvent(InputEvent event);
	
	public void addChild(GUIComponent child) {
		childComponents.add(child);
		child.parentComponent = this;
		child.updateScreenCoordinates();
	}
	
	public void setPosition(float relativeX, float relativeY) {
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		updateScreenCoordinates();
	}
	
	// If a parent exists, set screen coordinates relative to parent screen coordinates. Otherwise, set them to relative to 0
	// Update the screen coordinates of all child components, since they have likely become invalid
	protected void updateScreenCoordinates() {
		if (parentComponent != null) {
			screenX = parentComponent.screenX + relativeX;
			screenY = parentComponent.screenY + relativeY;
		} else {
			screenX = relativeX;
			screenY = relativeY;
		}
		for (GUIComponent child : childComponents) {
			child.updateScreenCoordinates();
		}
	}
	
	protected void updateChildComponents() {
		for (GUIComponent c : childComponents) {
			c.update();
		}
	}
	
	protected void renderChildComponents() {
		for (GUIComponent c : childComponents) {
			c.render();
		}
	}
	
	// Checks whether or not the passed screen coordinates are within the bounds of the component
	protected boolean isPointWithinBounds(float x, float y) {
		return (x>screenX && x<screenX+width && y>screenY && y<screenY+height);
	}
	
	public float getRelativeX() {
		return relativeX;
	}
	
	public float getRelativeY() {
		return relativeY;
	}
	
	public float getScreenX() {
		return screenX;
	}
	
	public float getScreenY() {
		return screenY;
	}
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
}
