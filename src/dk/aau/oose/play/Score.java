package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.Vec;

public class Score extends GameElement{
	private int score = 0;
	private Runner runner;
	private NoteLineView nlv;
	
	public Score(Runner runner){
		this.runner = runner;
		nlv = runner.getNoteLineView();
	}
	
	public void add(int score){
		
		if(score <= 0) 
			return;
		this.score += score;
		displayFloatingScore(score);
		
		System.out.println("Add to score: " + score + " totaling " + this.score);
	}
		
	private void displayFloatingScore(int value){
		Vector2f fsPos = Vec.add(runner.getPosition(), new Vector2f(0.0f, - runner.getDimensions().y));
		
		System.out.println("runner.getDimensions().y: " + runner.getDimensions().y);
		nlv.addChild(new FloatingScore(fsPos, value));
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
	
	public void reset(){
		score = 0;
	}
	
	public int getScore(){
		return score;
	}
	
	
}
