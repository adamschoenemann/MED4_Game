package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class Score extends GameElement{
	private int score = 0;
	
	public void add(int score, Vector2f position){
		System.out.println("Add to score: " + score + " totaling " + this.score);
		if(score <= 0) 
			return;
		this.score += score;
		displayFloatingScore(score, position);
	}
		
	private void displayFloatingScore(int value, Vector2f position){
		// TODO fill
		System.out.println("Floating score: " + value + " at " + position);
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Temporary solution
		gfx.setColor(Color.pink); //Temp color choice
		gfx.drawString(Integer.toString(score), getPosition().x, getPosition().y);		
	}
	
	
}
