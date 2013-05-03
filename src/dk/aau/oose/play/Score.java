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
		setBounds(50.0f, 30.0f);
	}
	
	public void add(int score){
		
		if(score <= 0) 
			return;
		this.score += score;
		displayFloatingScore(score);
	}
		
	private void displayFloatingScore(int value){
		//TODO currently, in PlayControllerTest, it seems that floats follow the runner. Not intended!
		Vector2f fsPos = Vec.add(runner.getPosition(), new Vector2f(0.0f, - (float) runner.getBounds().getHeight()));
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
