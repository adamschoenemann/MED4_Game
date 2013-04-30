package dk.aau.oose.play;

import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;

public class PlayThread extends Thread {
	
	private NoteLinePlayer nlp;
	private NoteLine nl;
	private long startTime;
	private int index = 0;
	
	public PlayThread(NoteLinePlayer nlp){
		this.nlp = nlp;
		nl = nlp.getNoteLine();
	}
	
	
	public NoteLinePlayer getNoteLinePlayer(){
		return nlp;
	}
	
	public long getElapsedTime(){
		return System.currentTimeMillis() - startTime;
	}
	
	public int getIndex(){
		return index;
	}
	
	public void setNextNoteIsPure(boolean val){
		nlp.setNextNoteIsPure(val);
	}
	
	@Override
	public void run(){
		startTime = System.currentTimeMillis();
		index = 0;
		int noteDuration = 1;
		for(int i = 0; i < nl.getNumBeats(); i += noteDuration){
			noteDuration = nlp.playNoteAt(i);
			index = i;
			try {
				Thread.sleep(noteDuration * nlp.getBeatDuration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread.currentThread().interrupt();
	}
	
}
