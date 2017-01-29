package com.lenwohl.dreammachine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import com.lenwohl.dreammachine.scenes.SceneManager;

public class DreamMachine extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Dream Machine";

	@Override
	public void create () {
		SceneManager.initialize();
		SceneManager.pushScene(SceneManager.EnumScene.MENU);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		SceneManager.update();
		SceneManager.render();
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		
	}

	@Override
	public void dispose () {
		
	}
}

