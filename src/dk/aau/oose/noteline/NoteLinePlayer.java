package dk.aau.oose.noteline;

import oscP5.OscMessage;
import dk.aau.oose.osc.MaxMSP;

public class NoteLinePlayer {
	
	public final static String LABEL = "note";
	
	private final NoteLine nl;
	private final int startOctave;
	private final int notesPerOctave;
	private final int beatDuration;
	private boolean nextNoteIsPure = false;

	

	/**
	 * 
	 * @param nl
	 * @param startOctave
	 * @param notesPerOctave
	 * @param tempo
	 */
	public NoteLinePlayer(NoteLine nl, int startOctave, int notesPerOctave, int tempo){
		this.nl = nl;
		this.startOctave = startOctave;
		this.notesPerOctave = notesPerOctave;
		this.beatDuration = 1000 * 60 / tempo;
	}
	
	public void play(){
		
		int noteDuration = 1;
		for(int i = 0; i < nl.getNumBeats(); i += noteDuration){
			//progressCallback.call(i * beatDuration);
			noteDuration = playNoteAt(i);
			try {
				Thread.sleep(noteDuration * beatDuration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Plays the target note.
	 * @param pos Position of wanted note in the NoteLine's Note array.
	 * @return Duration of target note in milliseconds.
	 */
	public int playNoteAt(int pos){
		int noteDuration = getNoteDurationAt(pos);
		playNote(nl.getNote(pos).getValue(), noteDuration * beatDuration);
		return noteDuration;
	}
	
	/**
	 * @param pos Position of wanted note in the NoteLine's Note array
	 * @return Duration of target note in milliseconds.
	 */
	public int getNoteDurationAt(int pos){
		int noteVal = nl.getNote(pos).getValue();
		// Play multi-beat notes
		int mult = 1;
		for(
				int i = pos;
				(i < (nl.getNumBeats() - 1)) 
				&& (nl.getNote(i).isDistinct() == false) 
				&& (nl.getNote(i + 1).getValue() == noteVal);
				i++){
			
			mult++;
		}
		
		return mult;
	}


	private void playNote(int note, int duration){
		// First calculate actual value based on octaves
		int octave = startOctave;
		if(note > notesPerOctave){
			int octaveOffset = (int) Math.floor((float)(note - 1) / notesPerOctave); 
			octave += octaveOffset;
			note -= octaveOffset * notesPerOctave;
			
		}
		sendNote(note, octave, duration);
	}
	
	private void sendNote(int value, int octave, long duration){
		OscMessage msg = new OscMessage(LABEL);
		msg.add(value);
		msg.add(octave);
		msg.add(duration);
		
		//boolean purity = true;
		//msg.add(purity);
		msg.add(nextNoteIsPure);
		nextNoteIsPure = false;
		
		System.out.format("Note: %d, %d, %d, %b\n", value, octave, duration, nextNoteIsPure); //purity
		MaxMSP.send(msg);
	}
	
	public NoteLine getNoteLine(){
		return nl;
	}
	
	public int getBeatDuration(){
		return beatDuration;
	}
	
	//TODO remove nextNoteIsPure from NoteLinePlayer
	public void setNextNoteIsPure(boolean nextNoteIsPure){
		this.nextNoteIsPure = nextNoteIsPure;
	}
	
	
	public static void main(String[] args) throws InterruptedException{
		MaxMSP.Connect("127.0.0.1", 7400);
		
		
		NoteLine nl = NoteLine.newTestInstance(5, 16);
		System.out.println(nl);
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 5, 180);
		
		for(int i = 0; i <= 30; i++){
			System.out.print(i + ": ");
			nlp.playNote(i, nlp.getBeatDuration());
			Thread.sleep(200);
		}
		
		//nlp.play();
		
	}
	

	
}
