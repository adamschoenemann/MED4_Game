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
	private int expectedKeyDownTimes [];
	private int nextJumpIndex = 0;
	private long startTime;
	
	private boolean nextNoteIsPerfect;
	
	private static final int TIME_FOR_PERFECT_NOTE = 15,
							 TIME_FOR_FAILED_NOTE = 100,
							 TIME_FOR_LISTENING_FOR_INPUT = 200, // TODO ok value?
							 START_TIME_OFFSET = 0;
						
	
	
	/**
	 * @param nlv 
	 * @param jumpKey Must accord to the values found in Input.KEY_...
	 */
	public PlayController(NoteLineView nlv, int jumpKey){
		System.out.println("Constructing PlayController...");
		this.nlv = nlv;
		this.nlp = nlv.getNoteLinePlayer();
		this.nl = nlp.getNoteLine();
		this.jumpKey = jumpKey;
		initiatePerfectJumpTimes();
		
		runner = new Runner(); //TODO probably update constructor parameters
		score =  new Score();  //TODO probably update constructor parameters
		System.out.println("Constructed PlayController.");
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	
	/**
	 * This method initiates the array perfectJumpTimes so that, beginning from time = 0 ms, it contains the relative time in millis that the user should hit for a perfect score.
	 */
	private void initiatePerfectJumpTimes(){
		System.out.println("Initiating perfect jump times...");
		int noOfBeats = nl.getNumBeats();
		
		assert noOfBeats > 0;
		
		expectedKeyDownTimes = new int[noOfBeats];
		expectedKeyDownTimes[0] = START_TIME_OFFSET;
		int expectedKeyDownTimesIndex = 1;
		for(int i = 0; i < noOfBeats; i++){
			if( i == 0 ||  nlp.getNoteLine().getNote(i - 1).isDistinct()){
				expectedKeyDownTimes[expectedKeyDownTimesIndex] = nlp.getNoteDurationAt(i) + expectedKeyDownTimes[expectedKeyDownTimesIndex - 1];
				expectedKeyDownTimesIndex++;
			}
			
		}
		
		System.out.println("Perfect jump times:");
		for(int i = 0; i < expectedKeyDownTimesIndex; i++){
			System.out.print("index " + i + ": " + expectedKeyDownTimes[i]);
			if(i > 0)
				System.out.println(" (difference from last is " + (expectedKeyDownTimes[i] - expectedKeyDownTimes[i -1]) + ")");
			else
				System.out.println();
		}
		System.out.println("Initiated perfect jump times.");		
	}

	@Override
	public void onUpdate() {
		int timeToNearestPerfectJump = getTimeToNearestPerfectJump();
		input = GameWorld.getGameContainer().getInput();
		
		
		//Check whether 
		//	1) we are close enough to a jump to care about user input and
		//	2) if that is the case, whether the user inputs correct stuff
		if(Math.abs(timeToNearestPerfectJump) < TIME_FOR_LISTENING_FOR_INPUT
			&& input.isKeyDown(jumpKey) ){
			
			if(Math.abs(timeToNearestPerfectJump) < TIME_FOR_FAILED_NOTE){
				nlp.setNextNoteIsPure(true);
			}  // Else leave next note at the default fail state
			
			
			//Calculate points, and display floating point thingy 
			
		}
		
		//	3) If so, 

				
	}

	@Deprecated
	private void updatePrecision() {
		
		// TODO Measure time off
		int timeToNearestPerfectJump = getTimeToNearestPerfectJump();
		
		
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
	
	private int getTimeToNearestPerfectJump(){
		return getNearestPerfectJumpTime() - getTimeSinceStart();
	}
	
	private int getTimeSinceStart(){
		return (int)(System.currentTimeMillis() - startTime);
	}
	
	private int getNearestPerfectJumpTime(){
		
		assert expectedKeyDownTimes.length > 0;
		
		int currentTime = getTimeSinceStart();
		int lastInterval = Math.abs(expectedKeyDownTimes[0] - currentTime);
		int index = 1;
		while(index < expectedKeyDownTimes.length){
			int nextInterval = Math.abs(expectedKeyDownTimes[index] - currentTime);
			if(nextInterval < lastInterval){
				lastInterval = nextInterval;
				index++;
			} else {
				break;
			}
		}
		return expectedKeyDownTimes[index - 1];
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
		
	}
	
}
