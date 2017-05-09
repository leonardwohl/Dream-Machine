package com.lenwohl.dreammachine.scenes;

import com.lenwohl.dreammachine.input.InputEvent;

import java.util.Stack;

public class SceneManager {
	
	public enum EnumScene {
		MENU
	}
	
	private static Stack<Scene> scenes;
	private static EnumScene sceneToPush;
	private static boolean popScene;
	
	public static void initialize() {
		scenes = new Stack<Scene>();
		sceneToPush = null;
		popScene = false;
	}
	
	// Exit and pop the top scene or push and initialize a new one
	// (if popScene, pushScene, switchScene, or reloadScene have been called)
	// Then update the current top scene
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
				case MENU: scenes.push(new MenuScene()); break;
			}
			sceneToPush = null;
			scenes.peek().init();
		}
		if (!scenes.isEmpty()) {
			scenes.peek().update();
		}
	}
	
	// Render the top scene
	public static void render() {
		if (!scenes.isEmpty()) {
			scenes.peek().render();
		}
	}
	
	public static void processInputEvent(InputEvent event) {
		if (event.handled) return;
		if (!scenes.isEmpty()) {
			scenes.peek().processInputEvent(event);
		}
	}
	
	// Push a scene at the next update
	public static void pushScene(EnumScene sceneID) {
		sceneToPush = sceneID;
	}
	
	// Pop the top scene at the next update
	public static void popScene() {
		popScene = true;
	}
	
	// Pop the top scene and push a new one at the next update
	public static void switchScene(EnumScene sceneID) {
		popScene = true;
		sceneToPush = sceneID;
	}
	
	// Pop the top scene and push it back on at the next update
	public static void reloadScene() {
		popScene = true;
		sceneToPush = scenes.peek().getSceneID();
	}
	
	public static EnumScene getCurrentSceneID() {
		if (!scenes.isEmpty()) return scenes.peek().getSceneID();
		else return null;
	}
	
}