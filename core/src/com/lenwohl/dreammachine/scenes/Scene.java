package com.lenwohl.dreammachine.scenes;

public abstract class Scene {
	
	protected SceneManager.EnumScene sceneID;
	protected Scene(SceneManager.EnumScene sceneID) {
		this.sceneID = sceneID;
	}
	
	public void init() { }
	public void update() { }
	public void render() { }
	public void exit() { }
	
	public SceneManager.EnumScene getSceneID() {
		return sceneID;
	}
	
}