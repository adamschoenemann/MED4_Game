package dk.aau.oose.play;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;


public class PlayPerfect extends PlayController {

	public PlayPerfect(NoteLineView nlv1, NoteLineView nlv2, int tempo) {
		super(setTempo(nlv1, tempo), setTempo(nlv2, tempo), 0);
	}
	
	private static NoteLineView setTempo(NoteLineView nlv, int tempo){
		NoteLinePlayer in =  nlv.getNoteLinePlayer();
		
		NoteLinePlayer nlp = new NoteLinePlayer(in.getNoteLine(), in.getStartOctave(), in.getNotesPerOctave(), tempo);
		
		return new NoteLineView(nlp, nlv.getWidth(), nlv.getHeight());
	}
	
	
}
