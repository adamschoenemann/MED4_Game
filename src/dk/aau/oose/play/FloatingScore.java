package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.util.MathUtils;
import dk.aau.oose.util.Vec;

public class FloatingScore extends GameElement {
	
	private int value;
	private int moveSpeed = 1;

	public FloatingScore(Vector2f position, int value){
		setPosition(position);
		this.value = value;
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
		//move up:
		this.setPosition(Vec.add(this.getPosition(), new Vector2f(0, -moveSpeed)));
		moveSpeed *= 2;
	}

	@Override
	public void onDraw(Graphics gfx) {
		
		// TODO Temporary solution
		gfx.setColor(calculateColor(value)); 
		gfx.drawString(Integer.toString(value), getPosition().x, getPosition().y);
	}
	
	private static Color calculateColor(int value){
		float r = (value < 50) ? 1.0f : (float)MathUtils.getValueOnLine(value/100.0, 50.0, 1.0, 100.0, 0.0);
		float g = (value > 50) ? 1.0f : (float)MathUtils.getValueOnLine(value/100.0, 0.0, 0.0, 50.0, 1.0);
		
		return new Color(r, g, 0.0f);
	}
	
	
	
	
}
