package com.lenwohl.dreammachine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lenwohl.dreammachine.audio.AudioManager;
import com.lenwohl.dreammachine.gui.Button;
import com.lenwohl.dreammachine.gui.Window;
import com.lenwohl.dreammachine.idlegame.Collector;
import com.lenwohl.dreammachine.main.DreamMachine;
import com.lenwohl.dreammachine.main.ResourceManager;
import com.lenwohl.dreammachine.rendering.RenderingManager;

public class SceneMenu extends Scene {
	
	private Texture bg;
	private Texture kev;
	private Texture yes;
	private Texture no;
	private Texture green;
	private BitmapFont font;
	private OrthographicCamera camera;
	private Collector collector;
	private int accumulatedPoints = 0;
	private Button kevButton;
	private Button yesButton;
	private Button noButton;
	private Window window;
	
	public SceneMenu() {
		super(SceneManager.EnumScene.MENU);
	}
	
	@Override
	public void init() {
		
		bg = ResourceManager.getTexture("bg1.png");
		kev = ResourceManager.getTexture("kevin.png");
		yes = ResourceManager.getTexture("yes.png");
		no = ResourceManager.getTexture("no.png");
		green = ResourceManager.getTexture("green.png");
		
		font = new BitmapFont();	// BitmapFont is a bad way of rendering font, but it works well enough for testing purposes
		font.getData().setScale(4);
		camera = new OrthographicCamera(DreamMachine.WIDTH, DreamMachine.HEIGHT);	// Camera management should be handled in RenderingManager
		camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0);
		camera.update();
		collector = new Collector(60.0f, 120.0f);
		
		window = new Window(10, 200, 400, 100, green);
		yesButton = new Button(50, 10, yes.getWidth() * 4, yes.getHeight() * 4, yes, new Button.Listener() {
			@Override
			public void onClick() {
				window.relativeY += 50;
			}
		});
		noButton = new Button(200, 10, no.getWidth() * 4, no.getHeight() * 4, no, new Button.Listener() {
			@Override
			public void onClick() {
				window.relativeY -= 50;
			}
		});
		window.addChild(yesButton);
		window.addChild(noButton);
		
		kevButton = new Button((DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2,
				kev.getWidth(), kev.getHeight(), kev, new Button.Listener() {
			@Override
			public void onClick() {
				accumulatedPoints += collector.getStoredPoints();
				collector.emptyStoredPoints();
				AudioManager.playSound("blip.mp3");
			}
		});
		
		AudioManager.playMusic("music.mp3", false);
		
	}
	
	@Override
	public void update() {
		
		window.update();
		kevButton.update();

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
	
		SpriteBatch sb = RenderingManager.getSpriteBatch();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		
		sb.draw(bg, 0, 0, DreamMachine.WIDTH, DreamMachine.HEIGHT);
		//sb.draw(kev, (DreamMachine.WIDTH/2)-kev.getWidth()/2, (DreamMachine.HEIGHT/2)-kev.getHeight()/2);
		
		window.render();
		kevButton.render();
		
		font.draw(sb, "Music Controls", 50, 790);
		font.draw(sb, "Pause: 1", 20, 720);
		font.draw(sb, "Resume: 2", 20, 640);
		font.draw(sb, "Stop: 3", 20, 560);
		font.draw(sb, "Play: 4", 20, 480);
		font.draw(sb, "Click Kev to collect", 0, 180);
		font.draw(sb, String.format("%.0f / %.0f   (%d)", collector.getStoredPoints(), collector.getMaximumStoredPoints(), accumulatedPoints), 50, 100);
		
		sb.end();
		
	}
	
	@Override
	public void exit() {
		ResourceManager.freeTexture(bg);
		ResourceManager.freeTexture(kev);
		ResourceManager.freeTexture(yes);
		ResourceManager.freeTexture(no);
		ResourceManager.freeTexture(green);
	}
	
}