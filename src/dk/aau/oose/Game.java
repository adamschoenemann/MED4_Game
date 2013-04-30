package dk.aau.oose;


import org.apache.log4j.BasicConfigurator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.noteline.NoteLinePlayer;
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
	public static String title = "MED4 Game";
	private GameController controller;
	
	public String getTitle(){
		return title;
	}
	
	public Game() throws SlickException {
		// Logging
		BasicConfigurator.configure();
		Log.d("Game starting...");
		
		GameContainer gc = GameWorld.getGameContainer();
		GameElement.setGameContainer(gc);
		controller = new GameController(10, 16);
		GameWorld.add(controller);
		
		
		
		
	}
	
	public void update() throws SlickException {
		
	}
}
