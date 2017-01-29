package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	
	// FIXME: Textures that are disposed remain in the map as empty textures and cannot be loaded again.
	
	private static Map<String, Texture> textures;
	
	public static void initialize() {
		textures = new HashMap<String, Texture>();
	}
	
	public static Texture getTexture(String filepath) {
		if (!textures.containsKey(filepath)) {
			textures.put(filepath, new Texture(filepath));
			System.out.println("a");
		}
		return textures.get(filepath);
	}
	
}
