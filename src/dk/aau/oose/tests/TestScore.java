package dk.aau.oose.tests;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlaybackIndicator;
import dk.aau.oose.play.Runner;
import dk.aau.oose.play.Score;
import dk.aau.oose.util.Vec;

public class TestScore extends BasicGame{

	private Score score;
	private NoteLineView nlv;
	private Runner runner;
	
	public TestScore() {
		super("Score test");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new TestScore());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(30);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		nlv.draw();
		score.draw();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		
		nlv = NoteLineView.newTestInstance(10, 16, 1, 5, 80, 700, 350);
		runner = new Runner(nlv);
		nlv.addChild(runner);
		score = new Score(runner);
		score.setPosition(gc.getWidth()/2, gc.getHeight()/2);
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
		if(gc.getInput().isMouseButtonDown(0)){
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			runner.setPosition(  nlv.globalToLocal(mouseX, mouseY));
		}
		if(gc.getInput().isMouseButtonDown(1)){
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			nlv.setPosition(mouseX, mouseY);
		}
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			score.add( (int)(Math.random() * 100.0));
			if(score.getScore() > 700)
				score.reset();
		}
		
		
		nlv.update();
		score.update();
		
	}

}
