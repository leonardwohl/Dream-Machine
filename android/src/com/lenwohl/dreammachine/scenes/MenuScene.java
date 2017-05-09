package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.gui.GUIComponent;
import com.lenwohl.dreammachine.gui.MenuSceneGUI;
import com.lenwohl.dreammachine.idlegame.Collector;
import com.lenwohl.dreammachine.main.AudioManager;
import com.lenwohl.dreammachine.main.DreamMachine;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.ResourceManager;
import com.lenwohl.dreammachine.main.RenderingManager;

import java.util.ArrayList;

public class MenuScene extends Scene {
	
	private Texture bgTex;
	private BitmapFont font;
	private OrthographicCamera camera;
	private MenuSceneGUI gui;
	
	public Collector collector;
	public int accumulatedPoints = 0;
	
	public MenuScene() {
		super(SceneManager.EnumScene.MENU);
	}
	
	@Override
	public void init() {
		
		bgTex = ResourceManager.getTexture("bg1.png");
		font = new BitmapFont();
		camera = new OrthographicCamera(DreamMachine.WIDTH, DreamMachine.HEIGHT);	// Camera management should be handled in RenderingManager
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		collector = new Collector(60.0f, 120.0f);
		gui = new MenuSceneGUI(this);
		gui.create();
		AudioManager.playMusic("music.mp3", false);
		
	}
	
	@Override
	public void update() {
		gui.update();
	}
	
	@Override
	public void render() {
	
		SpriteBatch sb = RenderingManager.getSpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		
		sb.draw(bgTex, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		gui.render();
		font.getData().setScale(2);
		font.draw(sb, "GPS:"+" "+DreamMachine.gpsInterface.getCurrentGPSPosition().toString(), 50, 600);
		font.getData().setScale(4);
		font.draw(sb, "Click Kev to collect", 0, 180);
		font.draw(sb, String.format("%.0f / %.0f   (%d)", collector.getStoredPoints(), collector.getMaximumStoredPoints(), accumulatedPoints), 50, 100);
		
		sb.end();
		
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
		if (event.handled) return;
		
		if (event.type == InputEvent.Type.KEY_DOWN && event.key == Input.Keys.ENTER) {
			// Print the full IDs of all gui components in the entire hierarchy
			ArrayList<GUIComponent> components = new ArrayList<GUIComponent>();
			components.add(gui.screenContainer);
			gui.screenContainer.getAllChildComponentsRecursive(components);
			for (GUIComponent c : components) {
				System.out.println(c.getFullID());
			}
		}
		
		gui.processInputEvent(event);
		
	}
	
	@Override
	public void exit() {
		ResourceManager.freeTexture(bgTex);
		font.dispose();
		gui.dispose();
	}
	
}