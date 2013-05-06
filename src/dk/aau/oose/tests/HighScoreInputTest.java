package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.osc.MaxMSP;
import dk.aau.oose.play.scores.HighScoreInput;
import dk.aau.oose.play.scores.HighScoreManager;
import dk.aau.oose.play.scores.HighScoreView;

public class HighScoreInputTest extends BasicGame {

	private HighScoreInput hsi;
	
	public HighScoreInputTest() {
		super("HighScoreInput test");
	}

	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {
			AppGameContainer container = new AppGameContainer(new HighScoreInputTest());
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
		
		hsi = new HighScoreInput(30);
		hsi.setPosition((gc.getWidth() - hsi.getWidth()) / 2,
				(gc.getHeight() - hsi.getHeight()) / 2);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		hsi.draw();
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		hsi.update();
	}

}

