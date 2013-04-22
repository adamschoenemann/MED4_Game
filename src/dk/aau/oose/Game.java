package dk.aau.oose;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameWorld;

/**
 * The main game class
 * All your "global" code should go here
 * @author Paolo Burelli
 */
public class Game {
	/*
	 * Set game title
	 */
	public static String title="MED4 Game";
	
	public Game() throws SlickException {
		GameContainer gc = GameWorld.getGameContainer();
		NoteMatrix nm = NoteMatrix.testInstance(5, 8);
		System.out.println(nm);
		NoteMatrixElement nme = new NoteMatrixElement(nm,
				gc.getWidth(), gc.getHeight());
		GameWorld.add(nme);
	}
	
	public void update() throws SlickException {
		/*
		 * Update game state
		 */
	}
}
