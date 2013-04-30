package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;

public class PlayTest extends BasicGame {
	
	public NoteLine nl;
	public NoteLinePlayer nlp;
	public NoteLineView nlv;
	
	public PlayTest() {
		super("PlayTest");
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		nlv.draw();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		nl = NoteLine.newTestInstance(10, 16);
		nlp = new NoteLinePlayer(nl, 1, 5, 160);
		nlv = new NoteLineView(nlp, 
				gc.getWidth(), 
				gc.getHeight());
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		try {
			AppGameContainer container = new AppGameContainer(new PlayTest());
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
