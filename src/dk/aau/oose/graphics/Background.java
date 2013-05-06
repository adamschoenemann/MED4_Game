package dk.aau.oose.graphics;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;



public class Background extends GameElement {

	private Image sprite;
	
	public Background(){
		try {
			//TODO use proper animation
			sprite = new Image("assets/bg.png"); // new Image("assets/runner/02.png"); //
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
