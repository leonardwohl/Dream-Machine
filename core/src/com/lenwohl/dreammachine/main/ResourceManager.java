package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
	
	private static Map<String, Resource<Texture>> textures;
	
	public static void initialize() {
		textures = new HashMap<String, Resource<Texture>>();
	}
	
	// If the texture hasn't been load yet, load it. Increment the number of instances of the texture, and return it
	public static Texture getTexture(String filepath) {
		if (!textures.containsKey(filepath)) {
			textures.put(filepath, new Resource<Texture>(new Texture(filepath)));
		}
		textures.get(filepath).numInstances++;
		return textures.get(filepath).data;
	}
	
	// Decrement the number of instances of the texture, and if it reaches zero, dispose of the texture and remove it from the map
	// In order to properly dispose of a resource, every call to getTexture() must be matched by a call to freeTexture()
	// Resources should never be disposed manually since the same resource is shared by multiple objects
	public static void freeTexture(Texture t) {
		for (Map.Entry<String, Resource<Texture>> entry : textures.entrySet()) {
			Resource<Texture> r = entry.getValue();
			if (r.data == t) {
				r.numInstances--;
				if (r.numInstances <= 0) {
					r.data.dispose();
					textures.remove(entry.getKey());
				}
				break;
			}
		}
	}
	
	// Simple wrapper for a resource containing a tracker for the number of times it has been requested but not freed
	private static class Resource<T> {
		public T data;
		public int numInstances;
		public Resource(T data) {
			this.data = data;
			this.numInstances = 0;
		}
	}
	
}
