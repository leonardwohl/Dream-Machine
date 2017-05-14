package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.input.InputHandler;
import com.lenwohl.dreammachine.scenes.MenuScene;
import com.lenwohl.dreammachine.scenes.SceneManager;

public class DreamMachine extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Dream Machine";
	public static GPSInterface gpsInterface;
	public InputHandler inputHandler;

	@Override
	public void create () {
		SceneManager.initialize();	// All static manager classes must be initialized
		ResourceManager.initialize();
		RenderingManager.initialize();
        AudioManager.initialize();
		SceneManager.pushScene(MenuScene.class);
		inputHandler = new InputHandler();
		Gdx.input.setInputProcessor(inputHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SceneManager.update();
		SceneManager.render();
		
	}

	public void processInputEvent(InputEvent event) {
		// A simple example of input handling:
		// Intercept a press of the Escape key to exit the app, or simply pass the input along.
		if (event.type == InputEvent.Type.KEY_DOWN) {
			if (event.key == Input.Keys.ESCAPE) {
				Gdx.app.exit();
				event.handled = true;
			}
		}
		SceneManager.processInputEvent(event);
	}
	
	@Override
	public void dispose () {
	}
	
	public static GPSInterface getGpsInterface() {
		return gpsInterface;
	}
	
}