package dk.aau.oose.play;

import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class Runner extends GameElement{
	
	private int currentCurve[];
	private Waypoints waypoint;
	private boolean nextJumpIsGood;

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}

	public void setNextJumpIsGood(boolean nextJumpIsGood) {
		this.nextJumpIsGood = nextJumpIsGood;
	}
	
	public void jump(){
		
	}
	
}
