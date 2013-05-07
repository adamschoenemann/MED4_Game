package dk.aau.oose.core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ButtonWithImage extends GameElement {

	private Image sprite;
	
	public ButtonWithImage(String image) {
		try {
			sprite = new Image(image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		setBounds(0, 0, sprite.getWidth(), sprite.getHeight());
		listen();
	}
	
	public void setImage(String image){
		try {
			sprite = new Image(image);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDraw(Graphics gfx) {
		sprite.draw();
	}

	public void updateImage() {
		
	}
}
