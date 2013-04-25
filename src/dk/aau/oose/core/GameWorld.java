package dk.aau.oose.core;

import java.util.ArrayList;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.Game;
import dk.aau.oose.IGameElement;

/**
 * Game support class
 * @author Paolo Burelli
 */

public class GameWorld extends BasicGame {
	
	private ArrayList<IGameElement> gameElements,addBuffer,removeBuffer;
	private GameContainer gameContainer;
	private static GameWorld instance;
	private Game game;

	/**
	 * Singleton constructor
	 * @return the singleton instance
	 */
	public static GameWorld getInstance(){
		if (instance == null)
			instance = new GameWorld();
		return instance;
	}
	
	/**
	 * Returns the game objects currently present on screen
	 * @return Current alive game objects
	 */
	public static ArrayList<IGameElement> getGameObjects() {
		return getInstance().gameElements;
	}

	/**
	 * Add one game object to the game
	 * @param go the game object to be added
	 */
	public static void add(IGameElement go){
		getInstance().addBuffer.add(go);
	}
	
	/**
	 * Add multiple game objects to the game
	 * @param <T>
	 * @param go a list containing all the game objects
	 */
	public static <T> void add(ArrayList<? extends IGameElement> gos){
		getInstance().addBuffer.addAll(gos);
	}
	
	/**
	 * Remove one game object from the game
	 * @param go the game object to be added
	 */
	public static void remove(IGameElement go){
		getInstance().removeBuffer.add(go);
	}
	
	/**
	 * Remove multiple game objects from the game
	 * @param <T>
	 * @param go a list containing all the game objects
	 */
	public static <T> void remove(ArrayList<? extends IGameElement> gos){
		getInstance().addBuffer.removeAll(gos);
	}
	
	/**
	 * Get number of game objects of a certain type
	 * @param specific game object class
	 * @return number of instances
	 */
	public static int getNumberOf(Class<? extends IGameElement> type){
		int count =0;
		for (IGameElement go:getGameObjects())
			if (type.isInstance(go))
				count++;
		return count;
	}
	
	
	/**
	 * Returns the game container object, useful to get screen size and the graphics object to draw
	 * @return game container
	 */
	public static GameContainer getGameContainer() {
		return getInstance().gameContainer;
	}
	
	
	/*
	public GameWorld(IGame game){
		super(game.getTitle());
		this.game = game;
		addBuffer = new ArrayList<IGameElement>();
		removeBuffer = new ArrayList<IGameElement>();
		gameElements = new ArrayList<IGameElement>();
		instance = this;
		
	}
	*/
	
	/**
	 * Constructor
	 * @param gameName Title of the game
	 */
	
	protected GameWorld()
	{
		super(Game.title);
		addBuffer = new ArrayList<IGameElement>();
		removeBuffer = new ArrayList<IGameElement>();
		gameElements = new ArrayList<IGameElement>();
	}
	
	
	/**
	 * Initialisation callback
	 */
	public void init(GameContainer gc) throws SlickException
	{
		//Assign container
		gameContainer = gc;
		
		game = new Game();
	}

	/**
	 * Periodic update function
	 */
	public void update(GameContainer gc, int delta) throws SlickException
	{
		//Update all the game objects
		for (IGameElement go: gameElements)
			go.update();
		
		//Clear removed objects
		for (IGameElement go: removeBuffer)
			gameElements.remove(go);
		removeBuffer.clear();
		
		//Add new objects
		for (IGameElement go: addBuffer)
			gameElements.add(go);
		addBuffer.clear();

		//Update game
		game.update();
	}

	/**
	 * Draw callback
	 */
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		//Draw all the game objects
		for (IGameElement go: gameElements)
			go.draw();
	}
}
