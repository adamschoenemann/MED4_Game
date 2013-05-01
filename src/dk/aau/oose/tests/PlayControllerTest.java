package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;

public class PlayControllerTest extends BasicGame {

	private PlayController pc;
	
	public PlayControllerTest() {
		super("PlayController test");
	}

	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new PlayControllerTest());
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
		NoteLineView nlv = NoteLineView.newTestInstance(10, 16, 2, 5, 60, 750, 300);
		pc = new PlayController(nlv, Input.KEY_Z);
		pc.setPosition(0, 200);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		pc.draw();
		
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		pc.update();
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			pc.startPlaying();
		}
		
	}

}
