package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.AudioManager;
import com.lenwohl.dreammachine.main.DreamMachine;
import com.lenwohl.dreammachine.main.ResourceManager;
import com.lenwohl.dreammachine.scenes.MenuScene;

public class MenuSceneGUI {
	
	private MenuScene scene;
	
	private Texture kevTex;
	private Texture yesTex;
	private Texture noTex;
	private Texture greenTex;
	private Texture purpleTex;
	private Texture playTex;
	private Texture pauseTex;
	private Texture stopTex;
	private Texture windowSkin;
	private BitmapFont font;
	
	public ScreenContainer screenContainer;
	
	public MenuSceneGUI(MenuScene scene) {
		this.scene = scene;
	}
	
	public void create() {
		
		kevTex = ResourceManager.getTexture("kevin.png");
		yesTex = ResourceManager.getTexture("yes.png");
		noTex = ResourceManager.getTexture("no.png");
		greenTex = ResourceManager.getTexture("green.png");
		purpleTex = ResourceManager.getTexture("purple.png");
		playTex = ResourceManager.getTexture("play.png");
		pauseTex = ResourceManager.getTexture("pause.png");
		stopTex = ResourceManager.getTexture("stop.png");
		windowSkin = ResourceManager.getTexture("window_skin.png");
		
		NinePatch windowSkinNP = new NinePatch(windowSkin);
		
		// Moving Window
		Window movingWindow = new Window("moving_window", 10, 200, 400, 100, windowSkinNP);
		movingWindow.addChild(new Button("yes_button", 50, 10,  yesTex.getWidth() * 4, yesTex.getHeight() * 4, new TextureRegion(yesTex), new Button.Listener() {
			@Override
			public void onClick() {
				Window w = (Window) screenContainer.getChild("moving_window");
				w.setPosition(w.getRelativeX(), w.getRelativeY() + 50);
			}
		}));
		movingWindow.addChild(new Button("no_button", 200, 10, noTex.getWidth() * 4, noTex.getHeight() * 4, new TextureRegion(noTex), new Button.Listener() {
			@Override
			public void onClick() {
				Window w = (Window) screenContainer.getChild("moving_window");
				w.setPosition(w.getRelativeX(), w.getRelativeY() - 50);
			}
		}));
		
		// Music Window
		Window musicWindow = new Window("music_window", 10, 650, 400, 100, windowSkinNP);
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
				scene.accumulatedPoints += scene.collector.getStoredPoints();
				scene.collector.emptyStoredPoints();
				AudioManager.playSound("blip.mp3");
			}
		});
		
		// GPS Control Window
		font = new BitmapFont(Gdx.files.internal("mcfont.fnt"));
		Window gpsWindow = new Window("gps_window", 10, 350, 100, 200, windowSkinNP);
		gpsWindow.addChild(new Button("len_button", 10, 120, 80, 50, new TextureRegion(purpleTex), new Button.Listener() {
			@Override
			public void onClick() {
				DreamMachine.gpsInterface.setVirtualGPSPosition(40.665463f, -74.292383f);	// Len's House
			}
		}));
		gpsWindow.getChild("len_button").addChild(new TextLabel("len_label", 10, 10, "Len", font));
		gpsWindow.addChild(new Button("kev_button", 10, 30, 80, 50, new TextureRegion(purpleTex), new Button.Listener() {
			@Override
			public void onClick() {
				DreamMachine.gpsInterface.setVirtualGPSPosition(40.659110f, -74.314690f);	// Kev's House
			}
		}));
		gpsWindow.getChild("kev_button").addChild(new TextLabel("gps_kev_label", 10, 10, "Kev", font));
		
		// Screen Container
		screenContainer = new ScreenContainer("gui_container");
		screenContainer.addChild(kevButton);
		screenContainer.addChild(musicWindow);
		screenContainer.addChild(gpsWindow);
		screenContainer.addChild(movingWindow);
		
	}
	
	public void update() {
		screenContainer.update();
	}
	
	public void render() {
		screenContainer.render();
	}
	
	public void processInputEvent(InputEvent event) {
		screenContainer.processInputEvent(event);
	}
	
	public void dispose() {
		ResourceManager.freeTexture(kevTex);
		ResourceManager.freeTexture(yesTex);
		ResourceManager.freeTexture(noTex);
		ResourceManager.freeTexture(greenTex);
		ResourceManager.freeTexture(purpleTex);
		ResourceManager.freeTexture(playTex);
		ResourceManager.freeTexture(pauseTex);
		ResourceManager.freeTexture(stopTex);
		ResourceManager.freeTexture(windowSkin);
		font.dispose();
	}
	
}
