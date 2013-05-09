package dk.aau.oose.play;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.MathUtils;

public class PlayTrack extends GameElement {


	private NoteLineView nlv;
	private int jumpKey;
	private int lastAcceptedNoteIndex;
	private PlaybackIndicator playbackIndicator;
	private Score score;
	private int pureTimeToNextNote; 
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
		} else {
			if(usesRunner){
				((Runner)playbackIndicator).stop();
			}
		}
		updatePosition(); // TODO inefficient; it should only update position when a change has happened. Put this here to ensure proper startup position. 
	}
	
	private void settleNextNotePurity() {
		
		int noteIndexNumber = playThread.getIndex();
		long timeToNextNote = playThread.getTimeToNextNote();
		long difference = pureTimeToNextNote - timeToNextNote;
		
		if(timeToNextNote < 0)
			return;
		
		if (	(lastAcceptedNoteIndex < noteIndexNumber &&  nlv.getNoteLinePlayer().getNoteLine().getNote(noteIndexNumber + 1).getValue() != 0) 
			||  (lastAcceptedNoteIndex == -1 			 &&  nlv.getNoteLinePlayer().getNoteLine().getNote(noteIndexNumber    ).getValue() != 0))  {
			if (difference > 0){
				lastAcceptedNoteIndex = noteIndexNumber;
				playThread.getNoteLinePlayer().setNextNoteIsPure(true);
				
				int points = (int)Math.round(MathUtils.scale(timeToNextNote, 0.0, pureTimeToNextNote, 100.0, 0.0));
				
				score.add(points);
			}
		} else {
			score.add(-30);
		}
	}

	public void startPlaying(){
		
		startPlaying(new PlayThread.Callback() {
				@Override
				public void call() {
					
				}
		});
	}
	
	public PlayThread startPlaying(PlayThread.Callback callback){
		if(score != null)
			score.reset();
		lastAcceptedNoteIndex = -1;
		if(playThread == null){
			playThread = new PlayThread(nlv.getNoteLinePlayer(), !usesRunner);
			playThread.setOnStopCallback(callback);
			playThread.start();
		}
		return playThread;
	}
	
	public boolean isPlaying(){
		if(playThread == null)
			return false;
		else
			return true;
	}
	
	public void stopPlaying(){
		if(playThread != null){
			playThread.stopPlaying();
			playThread = null;
		}
	}
	
	private void updatePosition(){
		float pbiX = playbackIndicator.getPosition().x;
		float windowWidth = GameElement.getGameContainer().getWidth();
		
		if(score != null)
			score.setPosition( pbiX + windowWidth * 0.5f - (score.getBounds().width + 70), -100.0f);
		setPosition( windowWidth * 0.5f - pbiX, getPosition().y);
	}

	public NoteLineView getNoteLineView() {
		return nlv;
	}
	
	public Score getScore(){
		return score;
	}
	
	public Thread getThread(){
		return playThread;
	}
	

}
