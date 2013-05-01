package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class Score extends GameElement{
	private int score = 0;
	
	public void add(int score, Vector2f position){
		
		if(score <= 0) 
			return;
		this.score += score;
		displayFloatingScore(score, position);
		
		System.out.println("Add to score: " + score + " totaling " + this.score);
	}
		
	private void displayFloatingScore(int value, Vector2f position){
		// TODO fill
		//System.out.println("Floating score: " + value + " at " + position);
		addChild(new FloatingScore(position, value));
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void onDraw(Graphics g) {
		// TODO Temporary solution
		g.setColor(Color.blue); //Temp color choice
		g.drawString(Integer.toString(score), 0, 0);
	}
	
	
}
