package dk.aau.oose.noteline;

import oscP5.OscMessage;
import dk.aau.oose.osc.MaxMSP;

public class NoteLinePlayer {
	
	public final static String LABEL = "note";
	
	private final NoteLine nl;
	private final int startOctave;
	private final int notesPerOctave;
	private final int sleepTime;
	

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
		this.sleepTime = 1000 * 60 / tempo;
	}
	
	public void play(){
		int length = 1;
		for(int i = 0; i < nl.getNumBeats(); i += length){
			length = playNoteAt(i);
			try {
				Thread.sleep(sleepTime * length);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int playNoteAt(int pos){
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
		playNote(nl.getNote(pos).getValue(), sleepTime * mult);
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
		
		boolean purity = true;
		msg.add(purity);
		
		System.out.format("Note: %d, %d, %d, %b\n", value, octave, duration, purity);
		MaxMSP.send(msg);
	}
	
	public NoteLine getNoteLine(){
		return nl;
	}
	
	public int getSleepTime(){
		return sleepTime;
	}
	
	public static void main(String[] args) throws InterruptedException{
		MaxMSP.Connect("127.0.0.1", 7400);
		
		
		NoteLine nl = NoteLine.newTestInstance(5, 16);
		System.out.println(nl);
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 5, 180);
		
		for(int i = 0; i <= 30; i++){
			System.out.print(i + ": ");
			nlp.playNote(i, nlp.getSleepTime());
			Thread.sleep(200);
		}
		
		//nlp.play();
		
	}
	

	
}
