package com.lenwohl.dreammachine.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.lenwohl.dreammachine.main.RenderingManager;

// Represents a scaleable texture for texturing windows, buttons, etc.
// The texture is divided into a grid of 9 regions which are scaled and drawn independently depending on the dimensions of the final image
public class NinePatch {
	
	private int s;	// The length of one of the sides of a region. Equal to the total width or height of the texture divided by 3
	private TextureRegion topLeft;
	private TextureRegion topCenter;
	private TextureRegion topRight;
	private TextureRegion centerLeft;
	private TextureRegion centerCenter;
	private TextureRegion centerRight;
	private TextureRegion bottomLeft;
	private TextureRegion bottomCenter;
	private TextureRegion bottomRight;
	
	public NinePatch(Texture texture) {
		this(new TextureRegion(texture));
	}
	
	public NinePatch(TextureRegion texture) {
		s = texture.getRegionWidth()/3;
		topLeft = 		new TextureRegion(texture, 0,	0, s, s);
		topCenter = 	new TextureRegion(texture, s, 	0, s, s);
		topRight =  	new TextureRegion(texture, s *2, 0, s, s);
		centerLeft =  	new TextureRegion(texture, 0, s, s, s);
		centerCenter =  new TextureRegion(texture, s, s, s, s);
		centerRight =  	new TextureRegion(texture, s *2, s, s, s);
		bottomLeft =  	new TextureRegion(texture, 0, 	s *2, s, s);
		bottomCenter =  new TextureRegion(texture, s, 	s *2, s, s);
		bottomRight =  	new TextureRegion(texture, s *2, s *2, s, s);
	}
	
	public void render(float x, float y, float w, float h) {
		SpriteBatch sb = RenderingManager.getSpriteBatch();
		//		Region			X		Y		W		H
		sb.draw(topLeft, 		x, 		y+h-s, 	s,		s);
		sb.draw(topCenter, 		x+s, 	y+h-s, 	w-(s*2),s);
		sb.draw(topRight, 		x+w-s, 	y+h-s, 	s,		s);
		sb.draw(centerLeft, 	x, 		y+s, 	s,		h-(s*2));
		sb.draw(centerCenter, 	x+s, 	y+s, 	w-(s*2),h-(s*2));
		sb.draw(centerRight, 	x+w-s, 	y+s, 	s,		h-(s*2));
		sb.draw(bottomLeft, 	x, 		y, 		s,		s);
		sb.draw(bottomCenter, 	x+s, 	y, 		w-(s*2),s);
		sb.draw(bottomRight, 	x+w-s, 	y, 		s,		s);
	}
	
}
