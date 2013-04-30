package dk.aau.oose.create;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayThread;

public class CreateController extends GameElement {
	
	public NoteLine nl;
	public NoteLineView nle;
	public NoteLinePlayer nlp;
	public PlayThread playThread;
	
	public CreateController(NoteLineView nle) {
		// NoteLine
		this.nle = nle;
		this.nlp = nle.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
		this.addChild(nle);
		nlp.progressCallback = new NoteLinePlayer.Callback() {
			
			@Override
			public void call(int progress) {
				System.out.println(progress);
			}
		};
		
	}
	
	@Override
	public void onUpdate() {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mx = input.getMouseX(), my = input.getMouseY();
			int noteHeight = nle.calculateNoteHeight(my);
			int index = nle.calculateNoteIndex(mx);
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
		if(input.isKeyPressed(Input.KEY_SPACE)){
			if(playThread == null){
				playThread = new PlayThread(nlp);
				playThread.start();
			}
		}
		if(playThread != null) {
			if(playThread.isAlive()) {
				System.out.format("Index: %d, elapsedTime: %d\n", 
						playThread.getIndex(), 
						playThread.getElapsedTime());
			} else {
				playThread = null;
			}
		}
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		long ms = System.currentTimeMillis();
		gfx.setColor(Color.green);
		gfx.fillOval(100f, 100f, (float) Math.cos(ms) * 100, (float) Math.sin(ms) * 100);
		
	}

}
