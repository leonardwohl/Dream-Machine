package com.lenwohl.dreammachine.gui;

import com.lenwohl.dreammachine.input.InputEvent;

import java.util.ArrayList;

// TODO: update screen coordinates if and only if they are changed, so that they don't have to be updated on every render or input

// GUI is made of up GUI Components. Each GUI Component may have a parent and any number of child components.
// Parents are resposible for calling update() and render() on their children. Children must set their screen coordinates
// relative to those of their parent, if one exists, so that if a parent moves, all children will move accordingly.
// Upon receiving input events, parents must pass them down to their children in reverse order (top-most components should
// recieve input events first, while bottom-most components are rendered first)
// Parents may intercept input events before passing them down and/or handle input events after they've passed through all
// children. This gives the most control over how inputs get processed while passing through the GUI component hierarchy
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
	
	public void processInputEvent(InputEvent event) {
		if (event.handled) return;	// Make sure the input hasn't already been handled
		interceptInputEvent(event);	// Do any processing necessary before passing the input to child components
		for (int i = childComponents.size()-1; i >= 0; i--) {	// Pass input to child components in reverse order (top-most child first)
			childComponents.get(i).processInputEvent(event);
		}
		handleInputEvent(event);	// Do any processing necessary after input has passed through child components
	}
	
	// This is called before passing input to child components
	protected abstract void interceptInputEvent(InputEvent event);
	// This is called after input has passed through all child components
	protected abstract void handleInputEvent(InputEvent event);
	
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
	
	// Checks whether or not the passed screen coordinates are within the bounds of the component
	protected boolean isPointWithinBounds(float x, float y) {
		updateScreenCoordinates();
		return (x>screenX && x<screenX+width && y>screenY && y<screenY+height);
	}
	
}
