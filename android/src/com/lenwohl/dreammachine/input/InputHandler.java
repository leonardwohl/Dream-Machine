package com.lenwohl.dreammachine.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.lenwohl.dreammachine.main.DreamMachine;

// Recieves input events from libgdx, wraps them in a custom InputEvent, and passes them to the DreamMachine instance
// The DreamMachine instance can handle the input itself (such as by exitting on ESC), or it can pass the input down along
// the input chain. Similarly, all other classes along the chain can handle the input and/or pass it down.
// The input chain is as follows: DreamMachine > SceneManager > Top Scene > Scene GUI > (Hierarchy of GUI components)
// A custom InputEvent allows the input to be passed down the chain in this way, simply, without making all classes implement
// InputProcessor, and gives us more control over input handling in general
public class InputHandler implements InputProcessor {
	
	private DreamMachine dm;
	
	public InputHandler() {
		dm = (DreamMachine)Gdx.app.getApplicationListener();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.KEY_DOWN;
		event.key = keycode;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.KEY_UP;
		event.key = keycode;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.TOUCH_DOWN;
		event.x = x;
		event.y = Gdx.graphics.getHeight() - y;	// Invert because libgdx input coords start at the top-left
		event.button = button;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.TOUCH_UP;
		event.x = x;
		event.y = Gdx.graphics.getHeight() - y;
		event.button = button;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.TOUCH_DRAG;
		event.x = x;
		event.y = Gdx.graphics.getHeight() - y;
		dm.processInputEvent(event);
		return false;
	}
	
	// Mouse moved, scrolled, and key typed might be useful for desktop debugging, maybe?
	@Override
	public boolean mouseMoved(int x, int y) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.MOUSE_MOVE;
		event.x = x;
		event.y = Gdx.graphics.getHeight() - y;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean scrolled(int amount) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.SCROLL;
		event.scroll = amount;
		dm.processInputEvent(event);
		return false;
	}
	
	@Override
	public boolean keyTyped(char character) {
		InputEvent event = new InputEvent();
		event.type = InputEvent.Type.CHAR_TYPED;
		event.character = character;
		dm.processInputEvent(event);
		return false;
	}
	
}
