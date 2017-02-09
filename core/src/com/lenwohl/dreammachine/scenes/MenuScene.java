package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lenwohl.dreammachine.gui.GUIComponent;
import com.lenwohl.dreammachine.gui.ScreenContainer;
import com.lenwohl.dreammachine.gui.TextLabel;
import com.lenwohl.dreammachine.main.AbstractGPSInterface;
import com.lenwohl.dreammachine.main.AudioManager;
import com.lenwohl.dreammachine.gui.Button;
import com.lenwohl.dreammachine.gui.Window;
import com.lenwohl.dreammachine.idlegame.Collector;
import com.lenwohl.dreammachine.main.DreamMachine;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.ResourceManager;
import com.lenwohl.dreammachine.main.RenderingManager;

import java.util.ArrayList;

public class MenuScene extends Scene {
	
	private Texture bgTex;
	private Texture kevTex;
	private Texture yesTex;
	private Texture noTex;
	private Texture greenTex;
	private Texture purpleTex;
	private Texture playTex;
	private Texture pauseTex;
	private Texture stopTex;
	
	private BitmapFont mainFont;
	private BitmapFont mcFont;
	private OrthographicCamera camera;
	private Collector collector;
	private int accumulatedPoints = 0;
    private AbstractGPSInterface gpsInterface;
	private ScreenContainer guiContainer;
	
	public MenuScene() {
		super(SceneManager.EnumScene.MENU);
	}
	
	@Override
	public void init() {
		
		bgTex = ResourceManager.getTexture("bg1.png");
		kevTex = ResourceManager.getTexture("kevin.png");
		yesTex = ResourceManager.getTexture("yes.png");
		noTex = ResourceManager.getTexture("no.png");
		greenTex = ResourceManager.getTexture("green.png");
		purpleTex = ResourceManager.getTexture("purple.png");
		playTex = ResourceManager.getTexture("play.png");
		pauseTex = ResourceManager.getTexture("pause.png");
		stopTex = ResourceManager.getTexture("stop.png");
		
		mainFont = new BitmapFont();
		camera = new OrthographicCamera(DreamMachine.WIDTH, DreamMachine.HEIGHT);	// Camera management should be handled in RenderingManager
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		collector = new Collector(60.0f, 120.0f);
        gpsInterface = DreamMachine.gpsInterface;
		
		// Moving Window
		Window movingWindow = new Window("moving_window", 10, 200, 400, 100, new TextureRegion(greenTex));
		movingWindow.addChild(new Button("yes_button", 50, 10,  yesTex.getWidth() * 4, yesTex.getHeight() * 4, new TextureRegion(yesTex), new Button.Listener() {
			@Override
			public void onClick() {
				Window w = (Window)guiContainer.getChild("moving_window");
				w.setPosition(w.getRelativeX(), w.getRelativeY() + 50);
			}
		}));
		movingWindow.addChild(new Button("no_button", 200, 10, noTex.getWidth() * 4, noTex.getHeight() * 4, new TextureRegion(noTex), new Button.Listener() {
			@Override
			public void onClick() {
				Window w = (Window)guiContainer.getChild("moving_window");
				w.setPosition(w.getRelativeX(), w.getRelativeY() - 50);
			}
		}));
		
		// Music Window
		Window musicWindow = new Window("music_window", 10, 650, 400, 100, new TextureRegion(greenTex));
		musicWindow.addChild(new Button("play_button", 10, 10, 64, 64, new TextureRegion(playTex), new Button.Listener() {
			@Override
			public void onClick() {
				AudioManager.playMusic("music.mp3", false);
			}
		}));
		musicWindow.addChild(new Button("pause_button", 80, 10, 64, 64, new TextureRegion(pauseTex), new Button.Listener() {
			@Override
			public void onClick() {
				if (AudioManager.isMusicPlaying()) {
					AudioManager.pauseMusic();
				} else {
					AudioManager.resumeMusic();
				}
			}
		}));
		musicWindow.addChild(new Button("stop_button", 150, 10, 64, 64, new TextureRegion(stopTex), new Button.Listener() {
			@Override
			public void onClick() {
				AudioManager.stopMusic();
			}
		}));
		
		// Kev Button
		Button kevButton = new Button("kev_button", (DreamMachine.WIDTH/2)-kevTex.getWidth()/2, (DreamMachine.HEIGHT/2)- kevTex.getHeight()/2,
				kevTex.getWidth(), kevTex.getHeight(), new TextureRegion(kevTex), new Button.Listener() {
			@Override
			public void onClick() {
				accumulatedPoints += collector.getStoredPoints();
				collector.emptyStoredPoints();
				AudioManager.playSound("blip.mp3");
			}
		});
		
		// GPS Control Window
		mcFont = new BitmapFont(Gdx.files.internal("mcfont.fnt"));
		Window gpsWindow = new Window("gps_window", 10, 350, 100, 200, new TextureRegion(greenTex));
		gpsWindow.addChild(new Button("len_button", 10, 120, 80, 50, new TextureRegion(purpleTex), new Button.Listener() {
			@Override
			public void onClick() {
				gpsInterface.setVirtualGPSPosition(40.665463f, -74.292383f);	// Len's House
			}
		}));
		gpsWindow.getChild("len_button").addChild(new TextLabel("len_label", 10, 10, "Len", mcFont));
		gpsWindow.addChild(new Button("kev_button", 10, 30, 80, 50, new TextureRegion(purpleTex), new Button.Listener() {
			@Override
			public void onClick() {
				gpsInterface.setVirtualGPSPosition(40.659110f, -74.314690f);	// Kev's House
			}
		}));
		gpsWindow.getChild("kev_button").addChild(new TextLabel("gps_kev_label", 10, 10, "Kev", mcFont));
		
		// Screen Container
		guiContainer = new ScreenContainer("gui_container");
		guiContainer.addChild(kevButton);
		guiContainer.addChild(musicWindow);
		guiContainer.addChild(gpsWindow);
		guiContainer.addChild(movingWindow);
		
		//AudioManager.playMusic("music.mp3", false);
		
	}
	
	@Override
	public void update() {
		
		guiContainer.update();
		
	}
	
	@Override
	public void render() {
	
		SpriteBatch sb = RenderingManager.getSpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		sb.draw(bgTex, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		guiContainer.render();	// Render all GUI components
		mainFont.getData().setScale(4);
		mainFont.draw(sb, "Click Kev to collect", 0, 180);
		mainFont.draw(sb, String.format("%.0f / %.0f   (%d)", collector.getStoredPoints(), collector.getMaximumStoredPoints(), accumulatedPoints), 50, 100);
		mainFont.getData().setScale(2);
		mainFont.draw(sb, "GPS:"+" "+gpsInterface.getCurrentGPSPosition().toString(), 50, 600);
		sb.end();
		
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
		if (event.handled) return;
		
		if (event.type == InputEvent.Type.KEY_DOWN) {
			if (event.key == Input.Keys.NUM_1) {
				AudioManager.pauseMusic();
			} else if (event.key == Input.Keys.NUM_2) {
				AudioManager.resumeMusic();
			} else if (event.key == Input.Keys.NUM_3) {
				AudioManager.stopMusic();
			} else if (event.key == Input.Keys.NUM_4) {
				AudioManager.playMusic("music.mp3", false);
			} else if (event.key == Input.Keys.ENTER) {
				// Print the full IDs of all gui components in the entire hierarchy
				ArrayList<GUIComponent> components = new ArrayList<GUIComponent>();
				components.add(guiContainer);
				guiContainer.getAllChildComponentsRecursive(components);
				for (GUIComponent c : components) {
					System.out.println(c.getFullID());
				}
			}
		}
		
		guiContainer.processInputEvent(event);
		
	}
	
	@Override
	public void exit() {
		ResourceManager.freeTexture(bgTex);
		ResourceManager.freeTexture(kevTex);
		ResourceManager.freeTexture(yesTex);
		ResourceManager.freeTexture(noTex);
		ResourceManager.freeTexture(greenTex);
		ResourceManager.freeTexture(purpleTex);
		ResourceManager.freeTexture(playTex);
		ResourceManager.freeTexture(pauseTex);
		ResourceManager.freeTexture(stopTex);
		mainFont.dispose();
		mcFont.dispose();
	}
	
}