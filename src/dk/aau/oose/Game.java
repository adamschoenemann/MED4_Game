package dk.aau.oose;


import org.apache.log4j.BasicConfigurator;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
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
		controller = new GameController();
		GameWorld.add(controller);
		
		
		
		
	}
	
	public void update() throws SlickException {
		
	}
}
