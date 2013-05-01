package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.play.Score;

public class ScoreTest extends BasicGame{

	private Score score;
	
	public ScoreTest() {
		super("Score test");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new ScoreTest());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(30);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		score.draw();
		g.setColor(Color.red);
		g.fillOval(10, 10, 10, 10);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		score = new Score();
		score.setPosition(gc.getWidth()/2, gc.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			score.add( (int)(Math.random() * 100.0), score.getPosition());
		}
		
		if(gc.getInput().isMousePressed(0)){
			System.out.println("mouse: " + gc.getInput().getMouseX() + ", " + gc.getInput().getMouseY());
		}
		
	}

}
