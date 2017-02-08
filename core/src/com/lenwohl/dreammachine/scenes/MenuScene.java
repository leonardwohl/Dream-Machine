package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	
	private ScreenContainer guiContainer;
	private Window movingWindow;
	private Window musicWindow;
	private Window gpsWindow;
	
	private BitmapFont mainFont;
	private BitmapFont mcFont;
	private OrthographicCamera camera;
	private Collector collector;
	private int accumulatedPoints = 0;
    private AbstractGPSInterface gpsInterface;
	
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
		
		Button yesButton = new Button(50, 10, yesTex.getWidth() * 4, yesTex.getHeight() * 4, yesTex, new Button.Listener() {
			@Override
			public void onClick() {
				movingWindow.setPosition(movingWindow.getRelativeX(), movingWindow.getRelativeY() + 50);
			}
		});
		Button noButton = new Button(200, 10, noTex.getWidth() * 4, noTex.getHeight() * 4, noTex, new Button.Listener() {
			@Override
			public void onClick() {
				movingWindow.setPosition(movingWindow.getRelativeX(), movingWindow.getRelativeY() - 50);
			}
		});
		movingWindow = new Window(10, 200, 400, 100, greenTex);
		movingWindow.addChild(yesButton);
		movingWindow.addChild(noButton);
		
		musicWindow = new Window(10, 650, 400, 100, greenTex);
		Button playButton = new Button(10, 10, 64, 64, playTex, new Button.Listener() {
			@Override
			public void onClick() {
				AudioManager.playMusic("music.mp3", false);
			}
		});
		Button pauseButton = new Button(80, 10, 64, 64, pauseTex, new Button.Listener() {
			@Override
			public void onClick() {
				if (AudioManager.isMusicPlaying()) {
					AudioManager.pauseMusic();
				} else {
					AudioManager.resumeMusic();
				}
			}
		});
		Button stopButton = new Button(150, 10, 64, 64, stopTex, new Button.Listener() {
			@Override
			public void onClick() {
				AudioManager.stopMusic();
			}
		});
		musicWindow.addChild(playButton);
		musicWindow.addChild(pauseButton);
		musicWindow.addChild(stopButton);
		
		Button kevButton = new Button((DreamMachine.WIDTH/2)- kevTex.getWidth()/2, (DreamMachine.HEIGHT/2)- kevTex.getHeight()/2,
				kevTex.getWidth(), kevTex.getHeight(), kevTex, new Button.Listener() {
			@Override
			public void onClick() {
				accumulatedPoints += collector.getStoredPoints();
				collector.emptyStoredPoints();
				AudioManager.playSound("blip.mp3");
			}
		});
		
		gpsWindow = new Window(10, 350, 100, 200, greenTex);
		Button gpsLenButton = new Button(10, 120, 80, 50, purpleTex, new Button.Listener() {
			@Override
			public void onClick() {
				gpsInterface.setVirtualGPSPosition(40.665463f, -74.292383f);	// Len's House
			}
		});
		Button gpsKevButton = new Button(10, 30, 80, 50, purpleTex, new Button.Listener() {
			@Override
			public void onClick() {
				gpsInterface.setVirtualGPSPosition(40.659110f, -74.314690f);	// Kev's House
			}
		});
		mcFont = new BitmapFont(Gdx.files.internal("mcfont.fnt"));
		TextLabel gpsLenLabel = new TextLabel(10, 10, "Len", mcFont);
		TextLabel gpsKevLabel = new TextLabel(10, 10, "Kev", mcFont);
		gpsLenButton.addChild(gpsLenLabel);
		gpsKevButton.addChild(gpsKevLabel);
		gpsWindow.addChild(gpsLenButton);
		gpsWindow.addChild(gpsKevButton);
		
		guiContainer = new ScreenContainer();
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