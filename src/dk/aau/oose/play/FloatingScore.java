package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class FloatingScore extends GameElement {
	
	private int value;

	public FloatingScore(Vector2f position, int value){
		setPosition(position);
		this.value = value;
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}
	
	
}
