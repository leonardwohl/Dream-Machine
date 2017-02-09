package com.lenwohl.dreammachine.gui;

import com.lenwohl.dreammachine.input.InputEvent;

import java.util.ArrayList;

// GUI is made of up GUI Components. Each GUI Component may have a parent and any number of child components.
// Parents are resposible for calling update() and render() on their children. Children must set their screen coordinates
// relative to those of their parent, if one exists, so that if a parent moves, all children will move accordingly.
// Parents are also responsible for passing input events down to their children. Components can handle and pass down input
// in any order, but in most cases, input should be passed down in reverse child component order (opposite of rendering order).
// Components have an ID that should be unique among siblings. Components can be retrived from parents with that ID.
public abstract class GUIComponent {
	
	protected float relativeX ;
	protected float relativeY;
	protected float screenX;
	protected float screenY;
	protected float width;
	protected float height;
	protected ArrayList<GUIComponent> childComponents;
	protected GUIComponent parentComponent;
	protected String id;
	
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
	}
	
	public abstract void update();
	public abstract void render();
	public abstract void processInputEvent(InputEvent event);
	
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
	
	public void addChild(GUIComponent child) {
		childComponents.add(child);
		child.parentComponent = this;
		child.updateScreenCoordinates();
	}
	
	// Get a child component by ID
	// TODO: Get a child of a child (...) by full ID
	public GUIComponent getChild(String id) {
		for (GUIComponent component : childComponents) {
			if (component.getID().equals(id)) return component;
		}
		return null;
	}
	
	// Fills the passed array with pointers to all components for which this one is a root
	public void getAllChildComponentsRecursive(ArrayList<GUIComponent> array) {
		for (GUIComponent child : childComponents) {
			array.add(child);
			child.getAllChildComponentsRecursive(array);
		}
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
	
	// Gets the (probably) unique ID of the component, which includes the path down the component hierarchy to it
	// If this is too inefficient, just store and return a full ID that's updated as necessary
	public String getFullID() {
		return (parentComponent == null) ? id : parentComponent.getFullID()+"/"+id;
	}
	
	public String getID() {
		return id;
	}
	
	public void setID(String id) {
		this.id = id;
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