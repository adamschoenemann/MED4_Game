package dk.aau.oose.play;

import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineElement;
import dk.aau.oose.noteline.NoteLinePlayer;

public class PlayController extends GameElement {
	
	private NoteLineElement nle;
	private NoteLinePlayer nlp;
	private NoteLine nl;
	
	public PlayController(NoteLineElement nle){
		this.nle = nle;
		this.nlp = nle.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}
	
}
