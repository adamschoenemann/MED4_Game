package dk.aau.oose.tests;



import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlaybackIndicator;
import dk.aau.oose.play.Runner;
import dk.aau.oose.play.Waypoints;


public class TestRunner extends BasicGame{


	private PlaybackIndicator runner;
	private NoteLineView nlv;
	private double progress = 0.0;
	private float steps[];
	
	private static final int MAX_NOTE_VALUE = 10;
	private static final int NO_OF_STEPS = 10;



	public TestRunner() {
		super("Runner Test");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		NoteLine nl = new NoteLine(MAX_NOTE_VALUE, 10);
		
		for(int i = 0; i < nl.getNumBeats(); i++){
			nl.setNoteValue((int)Math.round(Math.random() * MAX_NOTE_VALUE), i);
			System.out.println("note " + i + " is " + nl.getNote(i).getValue());
		}
		
		
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 5, 50);
		nlv = new NoteLineView(nlp, gc.getWidth() - 200, gc.getHeight() - 250);
		
		nlv.setPosition(new Vector2f(50, 50));
		
		
		runner = new Runner(nlv);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(nlv != null)
			nlv.draw();

		if(runner != null)
			runner.draw();
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		if(gc.getInput().isMouseButtonDown(0)){
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			nlv.setPosition(mouseX, mouseY);
		}
		
		if(gc.getInput().isKeyDown(Input.KEY_SPACE)){
			progress += 0.02;
			if(progress >= 1.0)
				progress = 1.0 - Double.MIN_VALUE;   
			System.out.println("progress: " + progress);
			System.out.println(runner.getPosition());
		}
		
		runner.move(progress);
		

	}


	public static void main(String[] argv) {
		System.out.println("running RunnerTest.");
		try {
			AppGameContainer container = new AppGameContainer(new TestRunner());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(30);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		
	}




}
