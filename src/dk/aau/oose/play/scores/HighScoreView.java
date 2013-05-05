package dk.aau.oose.play.scores;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;

public class HighScoreView extends GameElement {

	private HighScoreManager hsm;
	private String names[];
	private String scores[];
	private final static int WIDTH = 300,
							 HEIGHT = 200,
							 CORNER_RADIUS = 5,
							 NUMBER_OF_SCORES_DISPLAYED = 5;
	private final static Color COLOR = Color.blue.darker();
	private int horizontalDistance, verticalDistance;
	
	
	public HighScoreView(){
		float x = (float)(getGameContainer().getWidth() * 0.5) - (WIDTH / 2);
		float y = (float)(getGameContainer().getHeight() * 0.5) - (HEIGHT / 2);
		setBounds(WIDTH, HEIGHT);
		setPosition(x, y);
		
		hsm = new HighScoreManager();
		names  = new String[ Math.min(hsm.size(), NUMBER_OF_SCORES_DISPLAYED) ];
		scores = new String[ Math.min(hsm.size(), NUMBER_OF_SCORES_DISPLAYED) ];
		for(int i = 0; i < names.length; i++){
			names[i]  = hsm.getNameAt(i);
			scores[i] = hsm.getScoreAt(i);
		}
		
		horizontalDistance = WIDTH / 3;
		verticalDistance = HEIGHT / (names.length + 1);
		
	}
	
	public void onDraw(Graphics g) {
		g.setColor(COLOR);
		g.fillRoundRect(0.0f, 0.0f, getBounds().width, getBounds().height, CORNER_RADIUS);
		g.setColor(Color.white);
		
		g.drawString("Highscores", WIDTH / 2 - 50, 10);
		
		for(int i = 0; i < names.length; i++){
			
			g.drawString(names[i],  horizontalDistance    , verticalDistance * (i + 1));
			g.drawString(scores[i], horizontalDistance * 2, verticalDistance * (i + 1));
		}
	}	
}
