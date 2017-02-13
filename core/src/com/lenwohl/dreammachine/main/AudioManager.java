package com.lenwohl.dreammachine.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {

	// TODO: Play audio on a seperate thread
	// TODO: Manage individual instances of a sound
	
	private static final String AUDIO_DIR = "audio/";
	
	private static Music music;	// Music is streamed instead of loaded into memory completely, so it doesn't have to be cached
	private static String musicPath;
	private static Map<String, Sound> sounds;	// Sounds are loaded directly into memory, so they should be cached
	
	public static void initialize() {
		music = null;
		musicPath = null;
		sounds = new HashMap<String, Sound>();
	}
	
	// Stop the current music and play the new music (even if it's the same)
	public static void playMusic(String filename, boolean loop) {
		if(music != null) {
			stopMusic();
		}
		music = Gdx.audio.newMusic(Gdx.files.internal(AUDIO_DIR+filename));
		music.play();
		music.setLooping(loop);
		// Create an listener to dispose of the music when it's finished playing, and not looping
		music.setOnCompletionListener(new Music.OnCompletionListener() {
			@Override
			public void onCompletion(Music music) {
				if (!music.isLooping()) {
					stopMusic();
				}
			}
		});
	}
	
	// Add the sound to the cache, if it's not already loaded, and play an instance of it
	public static void playSound(String filename) {
		if (!sounds.containsKey(filename)) {
			sounds.put(filename, Gdx.audio.newSound(Gdx.files.internal(AUDIO_DIR+filename)));
		}
		// Half volume just cause the blip is fucking loud. Proper volume control should be added later.
		sounds.get(filename).play(0.5f);
	}
	
	// Stop and dispose the current music
	public static void stopMusic() {
		if (music != null) {
			music.stop();
			music.dispose();
			music = null;
			musicPath = null;
		}
	}
	
	public static void pauseMusic() {
		if (music != null) {
			music.pause();
		}
	}
	
	public static void resumeMusic() {
		if (music != null) {
			if (!music.isPlaying()) {
				music.play();
			}
		}
	}
	
	public static boolean isMusicPlaying() {
		if (music != null) {
			if (music.isPlaying()) {
				return true;
			}
		}
		return false;
	}
	
	public static String getMusicPath() {
		return musicPath;
	}
	
}
