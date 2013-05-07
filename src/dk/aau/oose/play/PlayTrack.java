package dk.aau.oose.play;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.scores.HighScoreInput;
import dk.aau.oose.play.scores.HighScoreManager;
import dk.aau.oose.play.scores.ScoreRecord;

public class PlayTrack extends GameElement {


	private NoteLineView nlv;
	private int jumpKey;
	private int lastAcceptedNoteIndex;
	private PlaybackIndicator playbackIndicator;
	private Score score;
	private int pureTimeToNextNote; 
	private static final int PURITY_DIFFERENCE_THRESHOLD = 100;
	private PlayThread playThread;
	private boolean usesRunner;

	/**
	 * @param nlv 
	 * @param jumpKey Must accord to the values found in Input.KEY_...
	 */
	public PlayTrack(NoteLineView nlv, int jumpKey, boolean usesRunner){
		this.nlv = nlv;
		this.jumpKey = jumpKey;
		this.setBounds(nlv.getBounds());
		this.usesRunner = usesRunner;
		
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
		
		// Subscribe to events
		GameElement.getGameContainer().getInput().addListener(this);
		
		this.addChild(playbackIndicator);
		listen();
	}

	private void updatePureTimeToNextNote(){
		pureTimeToNextNote = nlv.getNoteLinePlayer().getBeatDuration()/2;
	}

	@Override
	public void onUpdate() {
		nlv.update();
		
		if(playThread != null){
			if(playThread.isAlive() && !playThread.isInterrupted()){
				
				double progress;
				if(playThread.isInIntro()){
					progress = playThread.getIntroProgress() - 1.0;
				} else {
					long elapsedTime = playThread.getElapsedTime() ;
					long totalTime = playThread.getTotalTime();
					progress = (double)elapsedTime/totalTime;
				}

				playbackIndicator.move(progress);
				
				if(GameElement.getGameContainer().getInput().isKeyPressed(jumpKey)){
					settleNextNotePurity();
				}
			}
			else if(!playThread.isAlive()){
				playThread = null;
			}
		}	
		updatePosition(); // TODO inefficient; it should only update position when a change has happened. Put this here to ensure proper startup position. 
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

	public void startPlaying(){
		
		startPlaying(new PlayThread.Callback() {
				@Override
				public void call() {
					
				}
		});
	}
	
	public void startPlaying(PlayThread.Callback callback){
		if(score != null)
			score.reset();
		lastAcceptedNoteIndex = -1;
		if(playThread == null){
			playThread = new PlayThread(nlv.getNoteLinePlayer(), !usesRunner);
			playThread.setOnStopCallback(callback);
			playThread.start();
		}
	}
	
	public boolean isPlaying(){
		if(playThread == null)
			return false;
		else
			return true;
	}
	
	public void stopPlaying(){
		playThread.stopPlaying();
		playThread = null;
	}
	
	private void updatePosition(){
		float pbiX = playbackIndicator.getPosition().x;
		float windowWidth = GameElement.getGameContainer().getWidth();
		
		if(score != null)
			score.setPosition( pbiX + windowWidth * 0.5f - score.getBounds().width, 0.0f);
		setPosition( windowWidth * 0.5f - pbiX, getPosition().y);
	}

	public NoteLineView getNoteLineView() {
		return nlv;
	}
	
	


}
