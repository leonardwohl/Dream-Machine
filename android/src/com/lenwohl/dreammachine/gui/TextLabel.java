package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.lenwohl.dreammachine.input.InputEvent;
import com.lenwohl.dreammachine.main.RenderingManager;

// Just a string of text in a specific font
public class TextLabel extends GUIComponent {
	
	private String text;
	private BitmapFont font;
	private GlyphLayout glyphLayout;
	
	public TextLabel(String id, String text, BitmapFont font) {
		this(id, 0, 0, text, font);
	}
	
	public TextLabel(String id, float relativeX, float relativeY, String text, BitmapFont font) {
		super(id, relativeX, relativeY, 0, 0);
		this.text = text;
		this.font = font;
		this.glyphLayout = new GlyphLayout(font, text);
		width = glyphLayout.width;
		height = glyphLayout.height;
	}
	
	@Override
	public void update() {
		updateChildComponents();
	}
	
	@Override
	public void render() {
		// Add height because fonts render from the top-left, not bottom-left
		font.draw(RenderingManager.getSpriteBatch(), glyphLayout, screenX, screenY + height);
		renderChildComponents();
	}
	
	@Override
	public void processInputEvent(InputEvent event) {
	}
	
	@Override
	public void setDimensions(float width, float height) {
		// Dimensions are set by glyph layout. Ignore calls to setDimensions()
	}
	
	public void setText(String text) {
		this.text = text;
		glyphLayout.setText(font, text);	// Update glyph layout and component dimensions accordingly
		width = glyphLayout.width;
		height = glyphLayout.height;
	}
	
	public void setFont(BitmapFont font) {
		this.font = font;
		glyphLayout.setText(font, text);
		width = glyphLayout.width;
		height = glyphLayout.height;
	}
	
	public BitmapFont getFont() {
		return font;
	}
	
	public String getText() {
		return text;
	}
	
}
