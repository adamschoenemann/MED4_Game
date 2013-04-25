package dk.aau.oose.noteline;

import oscP5.OscMessage;
import dk.aau.oose.osc.MaxMSP;

public class NoteLinePlayer {
	
	public final static String LABEL = "note";
	
	private final NoteLine nl;
	private final int octave;
	private final int sleepTime;
	
	/**
	 * 
	 * @param nl
	 * @param octave
	 * @param tempo
	 */
	public NoteLinePlayer(NoteLine nl, int octave, int tempo){
		this.nl = nl;
		this.octave = octave;
		this.sleepTime = 1000 * 60 / tempo;
	}
	
	public void play(){
		for(int i = 0; i < nl.getNumBeats(); i++){
			playNoteAt(i);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void playNoteAt(int pos){
		sendNote(nl.getNote(pos), octave, sleepTime);
	}
	
	private void sendNote(int value, int octave, long duration){
		OscMessage msg = new OscMessage(LABEL);
		msg.add(value);
		msg.add(octave);
		msg.add(duration);
		System.out.format("Note: %d, %d, %d\n", value, octave, duration);
		MaxMSP.send(msg);
	}
	
	public NoteLine getNoteLine(){
		return nl;
	}
	
	public static void main(String[] args){
		MaxMSP.Connect("127.0.0.1", 7400);
		NoteLine nl = NoteLine.newTestInstance(5, 16);
		System.out.println(nl);
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 180);
		nlp.play();
	}
	

	
}
