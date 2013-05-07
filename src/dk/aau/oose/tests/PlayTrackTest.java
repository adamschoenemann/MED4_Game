package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.osc.MaxMSP;
import dk.aau.oose.play.PlayTrack;

public class PlayTrackTest extends BasicGame {

	private PlayTrack pc;
	private GameElement root;
	
	public PlayTrackTest() {
		super("PlayTrack test");
	}

	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {
			AppGameContainer container = new AppGameContainer(new PlayTrackTest());
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
		//NoteLineView.newTestInstance(steps, numBeats, startOctave, notesPerOctave, tempo, width, height)
		NoteLineView nlv = NoteLineView.newTestInstance(10, 32, 2, 5, 120, 2*750, 300);
		pc = new PlayTrack(nlv, Input.KEY_Z, true);
		pc.setPosition(0, 200);
		root = new GameElement();
		root.addChild(pc);
		
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		root.draw();
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		pc.update();
		
		if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
			pc.startPlaying();
		}
		
	}

}
