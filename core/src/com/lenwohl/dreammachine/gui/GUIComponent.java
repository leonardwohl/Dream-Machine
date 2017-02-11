package com.lenwohl.dreammachine.gui;

import com.lenwohl.dreammachine.input.InputEvent;

import java.util.ArrayList;

// GUI is made of up GUI Components. Each GUI Component may have a parent and any number of child components.
// Parents are resposible for calling update() and render() on their children. Children must set their screen coordinates
// relative to those of their parent, if one exists, so that if a parent moves, all children will move accordingly.
// Parents are also responsible for passing input events down to their children. Components can handle and pass down input
// in any order, but in most cases, input should be passed down in reverse child component order (opposite of rendering order).
// Components have an ID that should be unique among siblings. Components can be retrieved from parents with that ID.
public abstract class GUIComponent {
	
	protected float relativeX;	// Position relative to the parent component
	protected float relativeY;
	protected float screenX;
	protected float screenY;
	protected float width;
	protected float height;
	protected ArrayList<GUIComponent> childComponents;
	protected GUIComponent parentComponent;
	protected String id;
	protected String fullID;	// The (probably) unique ID of the component, which includes the path down the component hierarchy to it
	
	public GUIComponent(String id, float relativeX, float relativeY, float width, float height) {
		this.id = id;
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.width = width;
		this.height = height;
		screenX = relativeX;
		screenY = relativeY;
		childComponents = new ArrayList<GUIComponent>();
		parentComponent = null;
		fullID = id;
	}
	
	public abstract void update();
	public abstract void render();
	public abstract void processInputEvent(InputEvent event);
	
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
		for (GUIComponent c : childComponents) {
			c.updateScreenCoordinates();
		}
	}
	
	// Update the fullID of this component and all children in the case that the fullID has become invalid
	protected void recalculateFullID() {
		if (parentComponent == null) {
			fullID = id;
		} else {
			fullID = parentComponent.getFullID()+"/"+id;
		}
		for (GUIComponent c : childComponents) {
			c.recalculateFullID();
		}
	}
	
	// Checks whether or not the passed screen coordinates are within the bounds of the component
	protected boolean isPointWithinBounds(float x, float y) {
		return (x>screenX && x<screenX+width && y>screenY && y<screenY+height);
	}
	
	public void addChild(GUIComponent child) {
		childComponents.add(child);
		child.parentComponent = this;
		child.updateScreenCoordinates();
		child.recalculateFullID();
	}
	
	// Get a child component by id
	public GUIComponent getChild(String id) {
		for (GUIComponent c : childComponents) {
			if (c.getID().equals(id)) return c;
		}
		return null;
	}
	
	// Fills the passed array with references to all components for which this one is a root
	public void getAllChildComponentsRecursive(ArrayList<GUIComponent> array) {
		for (GUIComponent c : childComponents) {
			array.add(c);
			c.getAllChildComponentsRecursive(array);
		}
	}
	
	public void setID(String id) {
		this.id = id;
		recalculateFullID();
	}
	
	public void setPosition(float relativeX, float relativeY) {
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		updateScreenCoordinates();
	}
	
	public void setDimensions(float width, float height) {
		this.width = width;
		this.height = height;
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
	
	public String getID() {
		return id;
	}
	
	public String getFullID() {
		return fullID;
	}
	
}