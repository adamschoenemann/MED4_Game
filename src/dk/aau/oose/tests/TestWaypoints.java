package dk.aau.oose.tests;



import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.Waypoints;


public class TestWaypoints extends BasicGame {


	private Waypoints wp;
	private NoteLineView nlv;
	private float steps[];
	
	private static final int MAX_NOTE_VALUE = 10;
	private static final int NO_OF_STEPS = 10;



	public TestWaypoints() {
		super("Waypoints Test");
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
		
		wp = new Waypoints(nlv, NO_OF_STEPS);
		steps = wp.getSteps();
		wp.printSteps();
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(nlv != null)
			nlv.draw();

		for(int i = 0; i < steps.length; i++){
			
			if(i%NO_OF_STEPS == 0)
				g.setColor(Color.green);
			else
				g.setColor(Color.green.darker());
			
			Vector2f localCoords = wp.stepToNoteLineView(i);
			g.drawOval(localCoords.x - 3 + nlv.getPosition().x, localCoords.y - 3 + nlv.getPosition().y, 6, 6);
			
		}
		
		
		g.setColor(Color.orange);
		g.fillRect(nlv.getPosition().x - 3, nlv.getPosition().y - 3, 6, 6);
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		if(gc.getInput().isMouseButtonDown(0)){
			int mouseX = gc.getInput().getMouseX();
			int mouseY = gc.getInput().getMouseY();
			
			nlv.setPosition(mouseX, mouseY);
		}

	}


	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new TestWaypoints());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(100);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}




}
