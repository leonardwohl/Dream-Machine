package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.idlegame.Collector;
import com.lenwohl.dreammachine.main.DreamMachine;
import com.lenwohl.dreammachine.main.ResourceManager;
import com.lenwohl.dreammachine.rendering.RenderingManager;

public class SceneMenu extends Scene {
	
	private SpriteBatch sb;
	private Texture bg;
	private Texture kev;
	
	private BitmapFont font;
	private OrthographicCamera camera;
	private Collector collector;
	
	public SceneMenu() {
		super(SceneManager.EnumScene.MENU);
	}
	
	@Override
	public void init() {
		sb = RenderingManager.getSpriteBatch();
		bg = ResourceManager.getTexture("bg1.png");
		kev = ResourceManager.getTexture("kevin.png");
		
		// Just testing collector
		font = new BitmapFont();
		font.getData().setScale(5);
		camera = new OrthographicCamera(DreamMachine.WIDTH, DreamMachine.HEIGHT);
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		collector = new Collector(60.0f, 120.0f);
		
	}
	
	@Override
	public void update() {
		
		if (Gdx.input.justTouched()) {
			collector.emptyStoredPoints();
		}
		
	}
	
	@Override
	public void render() {
		
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(bg, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		sb.draw(kev, (DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2);
		font.draw(sb, String.format("%.0f / %.0f", collector.getStoredPoints(), collector.getMaximumStoredPoints()), 100, 100);
		sb.end();
		
	}
	
	@Override
	public void exit() {
		ResourceManager.freeTexture(bg);
		ResourceManager.freeTexture(kev);
	}
	
}