package dk.aau.oose;


import org.apache.log4j.BasicConfigurator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameWorld;
import dk.aau.oose.notmat.NoteMatrix;
import dk.aau.oose.notmat.NoteMatrixElement;
import dk.aau.oose.notmat.NoteMatrixPlayer;

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
		// Logging
		BasicConfigurator.configure();
		Log.d("Game starting...");
		
		GameContainer gc = GameWorld.getGameContainer();
		NoteMatrix nm = NoteMatrix.testInstance(5, 8);
		System.out.println(nm);
		int xOffset = 100;
		NoteMatrixElement nme = new NoteMatrixElement(nm,
				gc.getWidth() - xOffset, gc.getHeight());
		nme.setPosition(xOffset, 0);
		GameWorld.add(nme);
		nmp = nme.getPlayer();
		
	}
	
	public void update() throws SlickException {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isKeyPressed(Input.KEY_A)){
			nmp.play();
		}
		if(input.isKeyPressed(Input.KEY_D)){
			nmp.stop();
		}
		for(int i = Input.KEY_1; i < Input.KEY_0; i++){
			if(input.isKeyPressed(i)){
				int colIndex = i - 2;
				if(colIndex < nmp.getMatrix().getRowWidth()) {
					nmp.playColumnAt(colIndex);
//					System.out.println(colIndex);
				}
				
			}
		}
	}
}
