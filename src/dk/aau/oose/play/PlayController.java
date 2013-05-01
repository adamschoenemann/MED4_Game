package dk.aau.oose.play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.util.MathUtils;

public class PlayController extends GameElement {


	private NoteLineView nlv;
	private int jumpKey;
	private int lastAcceptedNoteIndex;
	private Runner runner;
	private Score score;

	//New
	private int pureTimeToNextNote; 
	private static final int PURITY_DIFFERENCE_THRESHOLD = 100;
	private PlayThread playThread;

	/**
	 * @param nlv 
	 * @param jumpKey Must accord to the values found in Input.KEY_...
	 */
	public PlayController(NoteLineView nlv, int jumpKey){
		this.nlv = nlv;
		this.jumpKey = jumpKey;
		updatePureTimeToNextNote();
		lastAcceptedNoteIndex = -1;

		runner = new Runner(nlv);
		score =  new Score(runner);

		this.addChild(runner);
		this.addChild(nlv);
		this.addChild(score);
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

				runner.testMove((double)elapsedTime/totalTime);

				if( GameElement.getGameContainer().getInput().isKeyPressed(jumpKey)){
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
		
		// TODO We need to do something so that it'll only accept one button push per note, so that you can't just idly push the button until you get it right.
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
