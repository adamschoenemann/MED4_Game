package dk.aau.oose;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.osc.MaxMSP;
import dk.aau.oose.tests.TestGameController;

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
	private GameElement root;

	public String getTitle(){
		return title;
	}

	public Game() throws SlickException {

		GameContainer gc = GameWorld.getGameContainer();
		GameElement.setGameContainer(gc);
		controller = new GameController();
		root = new GameElement();
		root.addChild(controller);
		GameWorld.add(root);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {

			AppGameContainer container = new AppGameContainer((org.newdawn.slick.Game) new Game());
			container.setDisplayMode(800,600,false);

			container.setMinimumLogicUpdateInterval(20);
			container.setShowFPS(false);
			container.start();

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}



	/*
		// Logging
		BasicConfigurator.configure();
		Log.d("Game starting...");
	 */
	//		GameContainer gc = GameWorld.getGameContainer();
	//		GameElement.setGameContainer(gc);
	//		controller = new GameController();
	//		root = new GameElement();
	//		root.addChild(controller);
	//		GameWorld.add(root);

	/*

		System.out.println("Starting game...");

		MaxMSP.Connect("127.0.0.1", 7400);
		System.out.println("Connected to Max...");
		try {

			//AppGameContainer container = new AppGameContainer((org.newdawn.slick.Game) new Game());
			AppGameContainer container = (AppGameContainer) GameWorld.getGameContainer();
			System.out.println("Created container...");
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(20);
			container.setShowFPS(false);

			GameContainer gc = GameWorld.getGameContainer();
			GameElement.setGameContainer(gc);
			controller = new GameController();
			root = new GameElement();
			root.addChild(controller);
			GameWorld.add(root);			

			System.out.println("Prepared everything...");

			container.start();
			System.out.println("Started.");

		} catch (SlickException e) {
			e.printStackTrace();
		}


	}


*/




	public void update() throws SlickException {

	}

	 
}
