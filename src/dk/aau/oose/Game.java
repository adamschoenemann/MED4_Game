package dk.aau.oose;


import org.apache.log4j.BasicConfigurator;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineElement;
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
	public NoteMatrixPlayer nmp;
	
	public String getTitle(){
		return title;
	}
	
	public Game() throws SlickException {
		// Logging
		BasicConfigurator.configure();
		Log.d("Game starting...");
		
		GameContainer gc = GameWorld.getGameContainer();
		
		/* NoteMatrix
		NoteMatrix nm = NoteMatrix.testInstance(5, 8);
		System.out.println(nm);
		int xOffset = 100;
		NoteMatrixElement nme = new NoteMatrixElement(nm,
				gc.getWidth() - xOffset, gc.getHeight());
		nme.setPosition(xOffset, 0);
		GameWorld.add(nme);
		nmp = nme.getPlayer();
		*/
		
		// NoteLine
		int maxNote = 10, numBeats = 16;
		NoteLine nl;
		//nl = NoteLine.newTestInstance(maxNote, numBeats);
		
		// Hand-made
		nl = new NoteLine(maxNote, numBeats);
		int[][] comp = {
				{1, 1}, {2, 1}, {3, 1}, {4, 1},
				{5, 0}, {5, 0}, {5, 1}, {5, 0},
				{9, 1}, {9, 0}, {1, 1}, {2, 0},
				{2, 0}, {5, 1}, {4, 1}, {1, 0}
		};
		assert(comp.length == numBeats);
		
		for(int i = 0; i < nl.getNumBeats(); i++){
			Note note = nl.getNote(i);
			note.setValue(comp[i][0]);
			note.setDistinct((comp[i][1] == 1) ? true : false);
		}
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 5, 180);
		NoteLineElement nle = new NoteLineElement(nlp, gc.getWidth(), gc.getHeight());
		nle.setColor(Color.blue);
		System.out.println(nl);
		GameWorld.add(nle);
		
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
