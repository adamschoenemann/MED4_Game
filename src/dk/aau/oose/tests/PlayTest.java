package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.osc.MaxMSP;
import dk.aau.oose.play.PlayThread;

public class PlayTest extends BasicGame {
	
	public NoteLine nl;
	public NoteLinePlayer nlp;
	public NoteLineView nlv;
	public PlayThread playThread;
	public GameContainer gc;
	public GameElement testEle;
	public GameElement distanceEle;
	
	public PlayTest() {
		super("PlayTest");
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException {
		nlv.draw();
		testEle.draw();
		distanceEle.draw();
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		this.gc = gc;
		nl = NoteLine.newTestInstance(10, 16);
		nlp = new NoteLinePlayer(nl, 1, 5, 160);
		nlv = new NoteLineView(nlp, 
				gc.getWidth(), 
				gc.getHeight());

		testEle = new GameElement(){
						
			@Override
			public void onDraw(Graphics gfx){
				gfx.setColor(Color.red);
				gfx.drawLine(0, 0, 0, GameElement.getGameContainer().getHeight());
			}
			
			
		};
		
		distanceEle = new GameElement(){
			
			@Override
			public void onDraw(Graphics gfx){
				gfx.setColor(Color.green);
				//gfx.drawLine(0, 0, globalToLocal(testEle.getPosition().x, 0f).x, 0);
				gfx.fillOval(0, 0, 10, 10);
			}
			
		};
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		if(playThread != null){
			if(playThread.isAlive() && !playThread.isInterrupted()){
				int index = playThread.getIndex();
				long elapsed = playThread.getElapsedTime() ;
				long nextTime = playThread.getNextNoteTime();
				long totalTime = playThread.getTotalTime();
				System.out.format("Index: %d, elapsedTime: %d, nextNoteTime: %d\n", 
						index, 
						elapsed,
						nextTime);
				
				testEle.setPosition(
						(float) elapsed / totalTime * gc.getWidth(),
						0);
				distanceEle.setPosition( ((elapsed + (elapsed - nextTime)) / (float) totalTime) * gc.getWidth(), gc.getHeight() / 2f);
			}
			else if(!playThread.isAlive()){
				playThread = null;
			}
		}		
	}
	
	
	
	@Override
	public void keyPressed(int key, char c){
		if(key == Input.KEY_SPACE){
			if(playThread == null){
				playThread = new PlayThread(nlp);
				playThread.start();
			}
		}
		
	}
	
	@Override
	public void mousePressed(int key, int x, int y){
		Input input = gc.getInput() ;
		if(key == Input.MOUSE_LEFT_BUTTON){
			float mx = x, my = y;
			int noteHeight = nlv.calculateNoteHeight(my);
			int index = nlv.calculateNoteIndex(mx);
			if(input.isKeyDown(Input.KEY_LSHIFT)){
				nl.setNoteValue(noteHeight, index);
			}
			else if(input.isKeyDown(Input.KEY_LCONTROL)){
				Note note = nl.getNote(index);
				note.setDistinct(!note.isDistinct());
				nl.setNote(note, index);
			}
			else {
				nlp.playNoteAt(index);
				System.out.println("Index: " + index);
			}
		}
	}
	
	public static void main(String[] args){
		try {
			MaxMSP.Connect("127.0.0.1", 7400);
			AppGameContainer container = new AppGameContainer(new PlayTest());
			container.setDisplayMode(800,600,false);
			container.setMinimumLogicUpdateInterval(20);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
