package dk.aau.oose.graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;

public class ImageElement extends GameElement {

	protected Image sprite;

	public ImageElement(String image) {
		try {
			sprite = new Image(image);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(1);
		}	
	}

	@Override
	public void onDraw(Graphics gfx) {
		sprite.draw();
	}

}