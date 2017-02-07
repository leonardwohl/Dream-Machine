package com.lenwohl.dreammachine.input;

public class InputEvent {

	public enum Type {
		TOUCH_DOWN,
		TOUCH_UP,
		TOUCH_DRAG,
		MOUSE_MOVE,
		KEY_DOWN,
		KEY_UP,
		CHAR_TYPED,
		SCROLL
	}
	
	public Type type = Type.TOUCH_DOWN;
	public float x = 0;
	public float y = 0;
	public int button = 0;
	public int key = 0;
	public char character = 'a';
	public float scroll = 0;
	
}