package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.osc.MaxMSP;
import dk.aau.oose.play.scores.HighScoreView;

public class TestHighScoreView extends BasicGame {

	private HighScoreView hsv;
	
	public TestHighScoreView() {
		super("HighScoreView test");
	}

	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {
			AppGameContainer container = new AppGameContainer(new TestHighScoreView());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(20);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		hsv = new HighScoreView();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		hsv.draw();
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		hsv.update();
	}

}
