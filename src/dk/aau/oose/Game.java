package dk.aau.oose;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
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
	public NoteMatrixPlayer nmp;
	
	
	public Game() throws SlickException {
		GameContainer gc = GameWorld.getGameContainer();
		NoteMatrix nm = NoteMatrix.testInstance(5, 8);
		System.out.println(nm);
		NoteMatrixElement nme = new NoteMatrixElement(nm,
				gc.getWidth(), gc.getHeight());
		GameWorld.add(nme);
		nmp = new NoteMatrixPlayer(nm, 180);
		
	}
	
	public void update() throws SlickException {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isKeyPressed(Input.KEY_A)){
			nmp.play();
		}
		if(input.isKeyPressed(Input.KEY_D)){
			nmp.stop();
		}
	}
}
