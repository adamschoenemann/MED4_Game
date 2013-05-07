package dk.aau.oose.play;

import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;

public class PlayThread extends Thread {
	
	private NoteLinePlayer nlp;
	private NoteLine nl;
	private long startTime;
	private int index = 0;
	private boolean stopFlag;
	//Intro
	public final static byte NUMBER_OF_COUNTIN_CLICKS = 4;
	private long introStartTime;
	private int introDuration;
	private boolean inIntro = true;

	public static interface Callback {
		public void call();
	}
	
	private Callback onStopped;
	
	public void setOnStopCallback(Callback cb){
		onStopped = cb;
	}
	
	public PlayThread(NoteLinePlayer nlp){
		this.nlp = nlp;
		nl = nlp.getNoteLine();
		stopFlag = false;
	}
	
	
	public NoteLinePlayer getNoteLinePlayer(){
		return nlp;
	}
	
	public long getElapsedTime(){
		return System.currentTimeMillis() - startTime;
	}
	
	public long getTotalTime(){
		return nl.getNumBeats() * nlp.getBeatDuration();
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public int getIndex(){
		return index;
	}
	
	public long getNextNoteTime(){
		if(index == nl.getNumBeats() - 1){
			return 0;
		}
		int i = index;
		while(nl.getNote(i).isDistinct() == false 
				&& nl.getNote(i).getValue() == nl.getNote(i + 1).getValue()){
			++i;
		}
		return ++i * nlp.getBeatDuration();
	}
	
	public long getTimeToNextNote(){
		return (getNextNoteTime() - getElapsedTime());
	}
	
	public void setNextNoteIsPure(boolean val){
		nlp.setNextNoteIsPure(val);
	}
	
	public boolean isInIntro(){
		return inIntro;
	}
	
	public long getIntroElapsedTime(){
		return System.currentTimeMillis() - introStartTime;
	}
	
	public long getIntroTimeLeft(){
		if(inIntro)
			return (introDuration - (System.currentTimeMillis() - introStartTime));
		else
			return -1;
	}
	
	public double getIntroProgress(){
		return ((double)getIntroElapsedTime() / (double)introDuration); //Goes from 0 to 1
	}
	
	public void stopPlaying(){
		stopFlag = true;
	}
	
	@Override
	public void run(){
		inIntro = true;
		introDuration = NUMBER_OF_COUNTIN_CLICKS * nlp.getBeatDuration();
		introStartTime = System.currentTimeMillis();
		for(byte i = 0; i < NUMBER_OF_COUNTIN_CLICKS; i++){
			if(stopFlag) break;
			nlp.playClick();
			try {
				Thread.sleep(nlp.getBeatDuration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		inIntro = false;	
		
		nlp.startRecording();
		
		startTime = System.currentTimeMillis();
		index = 0;
		setNextNoteIsPure(true); //Make first note pure; user can't do so in time.

		int noteDuration = 1;
		for(int i = 0; i < nl.getNumBeats(); i += noteDuration){
			if(stopFlag){
				nlp.stopRecording();
				break;
			}
			noteDuration = nlp.playNoteAt(i);
			index = i;
			try {
				Thread.sleep(noteDuration * nlp.getBeatDuration());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		nlp.stopRecording();
		if(onStopped != null){
			onStopped.call();
		}
		Thread.currentThread().interrupt();
	}
	
}
