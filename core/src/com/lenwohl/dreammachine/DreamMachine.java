package com.lenwohl.dreammachine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import States.MenuState;
import States.StateManager;

public class DreamMachine extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Dream Machine";

	private SpriteBatch sb;
	private Texture img;
	private StateManager sm;


	@Override
	public void create () {
		sb = new SpriteBatch();
		sm = new StateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		sm.push(new MenuState(sm));
	}

	@Override
	public void render () {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sm.update(Gdx.graphics.getDeltaTime());
		sm.render(sb);
	}

	@Override
	public void dispose () {
		sb.dispose();
	}
}

