package com.lenwohl.dreammachine.scenes;

import com.lenwohl.dreammachine.input.InputEvent;

public abstract class Scene {
	
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void processInputEvent(InputEvent event);
	public abstract void exit();
	
}