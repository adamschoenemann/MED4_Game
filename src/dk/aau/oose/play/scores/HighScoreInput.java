package dk.aau.oose.play.scores;

import org.newdawn.slick.gui.TextField;

import dk.aau.oose.core.GameElement;

public class HighScoreInput extends GameElement {
	
	private int score;
	private String name;
	private TextField txt;
	
	public HighScoreInput(int score){
		this.score = score;
	}
	
	
	
}
