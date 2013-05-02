package dk.aau.oose.play;

import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;

public class PlayController extends GameElement {


	private NoteLineView nlv;
	private int jumpKey;
	private int lastAcceptedNoteIndex;
	private PlaybackIndicator playbackIndicator;
	private Score score;
	private int pureTimeToNextNote; 
	private static final int PURITY_DIFFERENCE_THRESHOLD = 100;
	private PlayThread playThread;

	/**
	 * @param nlv 
	 * @param jumpKey Must accord to the values found in Input.KEY_...
	 */
	public PlayController(NoteLineView nlv, int jumpKey, boolean usesRunner){
		this.nlv = nlv;
		this.jumpKey = jumpKey;
		updatePureTimeToNextNote();
		lastAcceptedNoteIndex = -1;

		this.addChild(nlv);
		if(usesRunner){
			playbackIndicator = new Runner(nlv);
			score =  new Score((Runner)playbackIndicator);
			this.addChild(score);
		} else {
			playbackIndicator = new PlaybackLine(nlv); 
		} 

		this.addChild(playbackIndicator);
	}

	private void updatePureTimeToNextNote(){
		pureTimeToNextNote = nlv.getNoteLinePlayer().getBeatDuration()/2;
	}



	@Override
	public void onUpdate() {
		nlv.update();

		if(playThread != null){
			if(playThread.isAlive() && !playThread.isInterrupted()){
				long elapsedTime = playThread.getElapsedTime() ;
				long totalTime = playThread.getTotalTime();
				double progress = (double)elapsedTime/totalTime;

				playbackIndicator.move(progress);

				if(playbackIndicator instanceof PlaybackLine){
					playThread.getNoteLinePlayer().setNextNoteIsPure(true); //TODO inefficient; is set every frame atm.
				} else if(GameElement.getGameContainer().getInput().isKeyPressed(jumpKey)){
					settleNextNotePurity();
				}
			}
			else if(!playThread.isAlive()){
				playThread = null;
			}
		}	
	}

	private void settleNextNotePurity() {
		int noteIndexNumber = playThread.getIndex();
		long timeToNextNote = playThread.getTimeToNextNote();
		long difference = Math.abs(timeToNextNote - pureTimeToNextNote);
		
		if(lastAcceptedNoteIndex < noteIndexNumber && difference < PURITY_DIFFERENCE_THRESHOLD){
			lastAcceptedNoteIndex = noteIndexNumber;
			playThread.getNoteLinePlayer().setNextNoteIsPure(true);
			int points = (int)Math.round((float)difference*100.0f/PURITY_DIFFERENCE_THRESHOLD); 
			score.add(points);
			// TODO send do-not-stumble to runner here.
		}
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub

	}

	public void startPlaying(){
		lastAcceptedNoteIndex = -1;
		if(playThread == null){
			playThread = new PlayThread(nlv.getNoteLinePlayer());
			playThread.start();
		}
	}



}
