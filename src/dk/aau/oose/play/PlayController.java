package dk.aau.oose.play;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.noteline.NoteLinePlayer;

public class PlayController extends GameElement {
	

	private NoteLineView nlv;
	private NoteLinePlayer nlp;
	private NoteLine nl;
	
	private int jumpKey;
	private Runner runner;
	private Score score;
	private Input input;
	private int millisBetweenJumps;
	private int perfectJumpTimes [];
	private int nextJumpIndex;
	private long startTime;
	
	private static final int TIME_FOR_PERFECT_NOTE = 15,
							 TIME_FOR_FAILED_NOTE = 100;
						
	
	
	/**
	 * @param nlv 
	 * @param jumpKey Must accord to the final static keys found in Input.KEY_...
	 */
	public PlayController(NoteLineView nlv, int jumpKey){
		this.nlv = nlv;
		this.nlp = nlv.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
		this.jumpKey = jumpKey;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * This method initiates the array perfectJumpTimes so that, beginning from time = 0 ms, it contains the relative time in millis that the user should hit for a perfect score.
	 */
	private void initiatePerfectJumpTimes(){
		
	}

	@Override
	public void onUpdate() {
		input = GameWorld.getGameContainer().getInput();
		
		if(input.isKeyDown(jumpKey)){
			updatePrecision();
			
		}
				
	}

	private void updatePrecision() {
		// TODO Measure time off
		int absoluteTimeOff = 100; //Temp fixed number - should be the difference between the expected perfect timing and the user's actual timing.
		float precision;
		if(absoluteTimeOff <= TIME_FOR_PERFECT_NOTE){
			precision = 1.0f;
		} else if(absoluteTimeOff >= TIME_FOR_FAILED_NOTE) {
			precision = 0.0f;
		} else {
			precision = 1.0f - (((float)absoluteTimeOff - TIME_FOR_FAILED_NOTE) / (TIME_FOR_PERFECT_NOTE - TIME_FOR_FAILED_NOTE));
		}
		
		boolean nextJumpIsGood = (absoluteTimeOff < TIME_FOR_FAILED_NOTE) ? true : false;
		//pass precision information to runner (for animation)
		
		//pass precision information to score
		
		//pass precision information to music player
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}
	
}
