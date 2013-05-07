package dk.aau.oose.core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;

public class Button extends GameElement {
	
	private String text;
	private Color bgColor = Color.white, fgColor = Color.black;

	public Button(String text, float w, float h){
		setBounds(0, 0, w, h);
		setText(text);
		listen();
	}

	@Override
	public void onDraw(Graphics gfx) {
		gfx.setColor(bgColor);
		gfx.fillRect(0f, 0f, (float) getBounds().getWidth(), (float) getBounds().getHeight());
		gfx.setColor(fgColor);
		gfx.drawString(getText(), 0, 0);
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return this.text;
	}
	
	public void setBackgroundColor(Color col){
		bgColor = col;
	}
	
	public Color getBackgroundColor(){
		return bgColor;
	}
	
	public void setTextColor(Color col){
		fgColor = col;
	}
	
	public Color getTextColor(){
		return fgColor;
	}
	
}
