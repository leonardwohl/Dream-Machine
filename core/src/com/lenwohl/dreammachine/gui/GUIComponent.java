package com.lenwohl.dreammachine.gui;

import java.util.ArrayList;

// GUI is made of up GUI Components. Each GUI Component may have a parent and any number of child components.
// Parents are resposible for calling update() and render() on their children. Children must set their screen coordinates
// relative to those of their parent, if one exists, so that if a parent moves, all children will move accordingly
public abstract class GUIComponent {
	
	public float screenX = 0;
	public float screenY = 0;
	public float relativeX = 0;
	public float relativeY = 0;
	public float width = 0;
	public float height = 0;
	public ArrayList<GUIComponent> childComponents;
	public GUIComponent parentComponent;
	
	public GUIComponent(float relativeX, float relativeY, float width, float height) {
		this.relativeX = relativeX;
		this.relativeY = relativeY;
		this.width = width;
		this.height = height;
		childComponents = new ArrayList<GUIComponent>();
		parentComponent = null;
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public void addChild(GUIComponent child) {
		childComponents.add(child);
		child.parentComponent = this;
	}
	
	// If component has a parent component, set screen coordinates relative to those of parent
	// Otherwise, set screen coordinates equal to relative coordinates
	protected void updateScreenCoordinates() {
		if (parentComponent != null) {
			screenX = parentComponent.screenX + relativeX;
			screenY = parentComponent.screenY + relativeY;
		} else {
			screenX = relativeX;
			screenY = relativeY;
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
	
}
