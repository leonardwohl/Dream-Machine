package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.DreamMachine;

public class ScenePlay extends Scene {
	
	private SpriteBatch sb;
	
	private Texture bg;
	private Texture kev;
	
	public ScenePlay() {
		super(SceneManager.EnumScene.PLAY);
	}
	
	@Override
	public void init() {
		sb = new SpriteBatch();
		bg = new Texture("bg2.png");
		kev = new Texture("kevin.png");
	}
	
	@Override
	public void update() {
		if(Gdx.input.justTouched()) {
			SceneManager.switchScene(SceneManager.EnumScene.MENU);
		}
	}
	
	@Override
	public void render() {
		sb.begin();
		sb.draw(bg, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		sb.draw(kev, (DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2);
		sb.end();
	}
	
	@Override
	public void exit() {
		bg.dispose();
		kev.dispose();
	}
	
}