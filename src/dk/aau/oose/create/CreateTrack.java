package dk.aau.oose.create;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;

public class CreateTrack extends GameElement {
	
	public NoteLine nl;
	public NoteLineView nlv;
	public NoteLinePlayer nlp;
	//public PlayThread playThread;
	
	public CreateTrack(NoteLineView nlv) {
		// NoteLine
		this.nlv = nlv;
		this.nlp = nlv.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
		this.addChild(nlv);
		this.setDimensions(nlv.getDimensions());
	}
	
	@Override
	public void onUpdate() {
		handleInput();
		
		
		/*
		if(playThread != null) {
			if(playThread.isAlive()) {
				System.out.format("Index: %d, elapsedTime: %d, nextNoteTime: %d\n", 
						playThread.getIndex(), 
						playThread.getElapsedTime(),
						playThread.getNextNoteTime());
			} else {
				playThread = null;
			}
		}*/
		
	}

	private void handleInput() {
		
		/*
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mx = input.getMouseX(), my = input.getMouseY();
			int noteHeight = nlv.calculateNoteHeight(my);
			int index = nlv.calculateNoteIndex(mx);
			
			System.out.println(System.currentTimeMillis()%1000 + "\tMouse pressed on " + mx + ", " + my + "; noteHeight is " + noteHeight + " and noteIndex is " + index);
			
			
			if (noteHeight >= 0) {
				//int index = nlv.calculateNoteIndex(mx);
				if (input.isKeyDown(Input.KEY_LSHIFT)) {
					nl.setNoteValue(noteHeight, index);
				} else if (input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(input.KEY_RCONTROL)) {
					
					System.out.println("KEY_LCONTROL down - as it should be!");
					Note note = nl.getNote(index);
					note.setDistinct(!note.isDistinct());
					nl.setNote(note, index);
				} else {
					nlp.playNoteAt(index);
					System.out.println("Index: " + index);
				}
			}
		}*/
	/*	if(input.isKeyPressed(Input.KEY_SPACE)){
			if(playThread == null){
				playThread = new PlayThread(nlp);
				playThread.start();
			}
		}*/
	
	}
	
	public void moveTrack(float numberOfPixels){
		setPosition(getPosition().x + numberOfPixels, getPosition().y);
	}
	
	@Override
	public void mouseClicked(int btn, int x, int y, int clickCount) {
		
		if(getParent() != null){
			Input input = getGameContainer().getInput();
			int noteHeight = nlv.calculateNoteHeight(y);
			int noteIndex = nlv.calculateNoteIndex(x);

			//System.out.println(System.currentTimeMillis()%1000 + "\tMouse pressed on " + mx + ", " + my + "; noteHeight is " + noteHeight + " and noteIndex is " + index);

			if (noteHeight >= 0 && noteIndex >= 0) {
				//int index = nlv.calculateNoteIndex(mx);
				//if (input.isKeyDown(Input.KEY_LSHIFT)) {
				nl.setNoteValue(noteHeight, noteIndex);
				/*} else if (input.isKeyDown(Input.KEY_LCONTROL) || input.isKeyDown(input.KEY_RCONTROL)) {

				System.out.println("KEY_LCONTROL down - as it should be!");
				Note note = nl.getNote(noteIndex);
				note.setDistinct(!note.isDistinct());
				nl.setNote(note, noteIndex);
			} else {
				nlp.playNoteAt(noteIndex);
				System.out.println("Index: " + noteIndex);
			}*/
			}
		}
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		
		/*
		long ms = System.currentTimeMillis()/100;
		gfx.setColor(Color.green);
		gfx.fillOval(100f, 100f, (float) Math.cos(ms) * 100, (float) Math.sin(ms) * 100);
		*/
	}

	public NoteLineView getNoteLineView() {
		return nlv;
	}
	
	

}
