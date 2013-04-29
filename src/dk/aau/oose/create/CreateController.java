package dk.aau.oose.create;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.noteline.NoteLinePlayer;

public class CreateController extends GameElement {
	
	public NoteLine nl;
	public NoteLineView nle;
	public NoteLinePlayer nlp;
	
	public CreateController(NoteLineView nle) {
		// NoteLine
		this.nle = nle;
		this.nlp = nle.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
		this.addChild(nle);
				
		
	}
	
	@Override
	public void onUpdate() {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mx = input.getMouseX(), my = input.getMouseY();
			if(input.isKeyDown(Input.KEY_LSHIFT)){
				int noteHeight = nle.calculateNoteHeight(my);
				int index = nle.calculateNoteIndex(mx);
				nl.setNoteValue(noteHeight, index);
			} else {
				int index = nle.calculateNoteIndex(mx);
				nlp.playNoteAt(index);
				System.out.println("Index: " + index);
			}
		}
		if(input.isKeyPressed(Input.KEY_SPACE)){
			nlp.play();
		}
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}

}
