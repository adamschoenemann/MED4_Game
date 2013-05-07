package dk.aau.oose.create;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.Note;
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
		this.setBounds(nlv.getBounds());
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
				if (input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(input.KEY_RSHIFT)) {

					Note note = nl.getNote(noteIndex);
					note.setDistinct(!note.isDistinct());
					nl.setNote(note, noteIndex);
				} else {
					nl.setNoteValue(noteHeight, noteIndex);
				}
			}  
		}
	}
		
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		Input input = getGameContainer().getInput();
		if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			int noteHeight = nlv.calculateNoteHeight(newy);
			int noteIndex = nlv.calculateNoteIndex(newx);
			if(noteHeight >= 0 && noteIndex >= 0){
				nl.setNoteValue(noteHeight, noteIndex);
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
