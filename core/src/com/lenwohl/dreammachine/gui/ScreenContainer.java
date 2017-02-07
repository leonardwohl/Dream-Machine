package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.Gdx;
import com.lenwohl.dreammachine.input.InputEvent;

// A simple container for other GUI components that matches the dimensions of the app screen
public class ScreenContainer extends GUIComponent {
	
	public ScreenContainer() {
		super(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	@Override
	public void update() {
		updateChildComponents();
	}
	
	@Override
	public void render() {
		renderChildComponents();
	}
	
	@Override
	protected void interceptInputEvent(InputEvent event) {
	}
	
	@Override
	protected void handleInputEvent(InputEvent event) {
	}
	
}
