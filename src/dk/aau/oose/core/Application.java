package dk.aau.oose.core;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import dk.aau.oose.Game;
import dk.aau.oose.notmat.NoteMatrix;
import dk.aau.oose.osc.MaxMSP;

/**
 * Application starting point
 * @author Paolo Burelli
 */
public class Application {
	public static void main(String[] args) throws SlickException {
		MaxMSP.Connect("127.0.0.1", 7400);
		//Creates a new instance of the game engine
		AppGameContainer app = new AppGameContainer(GameWorld.getInstance());
		//Sets the resolution
		app.setDisplayMode(1200, 400, false);
		//Sets the update interval to 50 times per second
		app.setMinimumLogicUpdateInterval(20);
		//Starts the game
		app.start();
	}
}