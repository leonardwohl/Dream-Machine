package com.lenwohl.dreammachine.scenes;

import java.util.Stack;

public class SceneManager {
	
	public enum EnumScene {
		MENU,
		PLAY
	}
	
	private static Stack<Scene> scenes;
	private static EnumScene sceneToPush;
	private static boolean popScene;
	
	public static void initialize() {
		scenes = new Stack<Scene>();
		sceneToPush = null;
		popScene = false;
	}
	
	public static void update() {
		if (popScene) {
			if (!scenes.isEmpty()) {
				scenes.peek().exit();
				scenes.pop();
			}
			popScene = false;
		}
		if (sceneToPush != null) {
			switch (sceneToPush) {
				case MENU: scenes.push(new SceneMenu()); break;
				case PLAY: scenes.push(new ScenePlay()); break;
			}
			sceneToPush = null;
			scenes.peek().init();
		}
		if (!scenes.isEmpty()) {
			scenes.peek().update();
		}
	}
	
	public static void render() {
		if (!scenes.isEmpty()) {
			scenes.peek().render();
		}
	}
	
	public static void pushScene(EnumScene sceneID) {
		sceneToPush = sceneID;
	}
	
	public static void popScene() {
		popScene = true;
	}
	
	public static void switchScene(EnumScene sceneID) {
		popScene = true;
		sceneToPush = sceneID;
	}
	
	public static void reloadScene() {
		popScene = true;
		sceneToPush = scenes.peek().getSceneID();
	}
	
	public static EnumScene getCurrentSceneID() {
		if (!scenes.isEmpty()) return scenes.peek().getSceneID();
		else return null;
	}
	
}