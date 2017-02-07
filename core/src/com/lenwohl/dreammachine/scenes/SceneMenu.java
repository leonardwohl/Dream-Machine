package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.audio.AudioManager;
import com.lenwohl.dreammachine.gps.AbstractGPSInterface;
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
	private int accumulatedPoints = 0;
    private AbstractGPSInterface gpsInterface;

	
	public SceneMenu() {
		super(SceneManager.EnumScene.MENU);
	}
	
	@Override
	public void init() {
		
		sb = RenderingManager.getSpriteBatch();
		bg = ResourceManager.getTexture("bg1.png");
		kev = ResourceManager.getTexture("kevin.png");
		font = new BitmapFont();	// BitmapFont is a bad way of rendering font, but it works well enough for testing purposes
		font.getData().setScale(4);
		camera = new OrthographicCamera(DreamMachine.WIDTH, DreamMachine.HEIGHT);	// Camera management should be handled in RenderingManager
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		collector = new Collector(60.0f, 120.0f);
        gpsInterface = DreamMachine.gpsInterface;
		AudioManager.playMusic("music.mp3", false);
		
	}
	
	@Override
	public void update() {
		
		if (Gdx.input.justTouched()) {
			accumulatedPoints += collector.getStoredPoints();
			collector.emptyStoredPoints();
			AudioManager.playSound("blip.mp3");
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
			AudioManager.pauseMusic();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
			AudioManager.resumeMusic();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
			AudioManager.stopMusic();
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
			AudioManager.playMusic("music.mp3", false);
		}
		
	}
	
	@Override
	public void render() {
		
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(bg, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		sb.draw(kev, (DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2);
		font.draw(sb, "Music Controls", 50, 790);
		font.draw(sb, "Pause: 1", 20, 720);
		font.draw(sb, "Resume: 2", 20, 640);
		font.draw(sb, "Stop: 3", 20, 560);
		font.draw(sb, "Play: 4", 20, 480);
		font.draw(sb, "Touch to collect", 50, 180);
		font.draw(sb, String.format("%.0f / %.0f   (%d)",
				collector.getStoredPoints(), collector.getMaximumStoredPoints(), accumulatedPoints), 50, 100);
        font.draw(sb, "GPS:"+" "+gpsInterface.getCurrentGPSPosition().toString(), 100, 300);
		sb.end();
		
	}
	
	@Override
	public void exit() {
		ResourceManager.freeTexture(bg);
		ResourceManager.freeTexture(kev);
	}
	
}