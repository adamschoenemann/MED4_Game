package dk.aau.oose.play.scores;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class HighScoreView extends GameElement {

	private static final int LINE_HEIGHT = 20;

	private HighScoreManager hsm;

	private final static int CORNER_RADIUS = 5,
							 NUMBER_OF_SCORES_DISPLAYED = 5;
	private final static Color COLOR = Color.blue.darker();
		
	public HighScoreView(){

		hsm = HighScoreManager.getInstance();

		
	}
	
	public void onDraw(Graphics g) {
		g.setColor(COLOR);
		g.fillRoundRect(0.0f, 0.0f, getWidth(), getHeight(), CORNER_RADIUS);
		g.setColor(Color.white);
		
		for(int i = 0; i < Math.min(NUMBER_OF_SCORES_DISPLAYED, hsm.size()); i++){
			ScoreRecord rec = hsm.getScoreRecordAt(i);
			drawCenteredString(rec.getName() + ": " + rec.getScore(), g, 20 + LINE_HEIGHT * i);
		}
		
		drawCenteredString("Highscores", g, 0);
	}
	
	private void drawCenteredString(String str, Graphics g, float y){
		g.drawString(str, (getWidth() - g.getFont().getWidth(str)) / 2, y);
	}
}
