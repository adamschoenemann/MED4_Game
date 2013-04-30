package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.objectweb.asm.tree.IntInsnNode;

import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.MathUtils;


// TODO Make interpolations into curves rather than straight lines.

public class Waypoints {
	
	private NoteLineView noteLineView;
	private int numberOfStepsPerNote;
	private int currentNoteIndex = -1;
	private float steps[];
	private int currentStep = 0;
	
	
	public Waypoints(NoteLineView noteLineView, int numberOfStepsPerNote){
		this.noteLineView = noteLineView;
		this.numberOfStepsPerNote = numberOfStepsPerNote;
		initiateSteps(noteLineView.getNoteLinePlayer().getNoteLine()); 
	}
	
	public static void main(String[] args){
		System.out.println("Testing Waypoints...");
		NoteLine notes = new NoteLine(10, 20);
		NoteLinePlayer nlp = new NoteLinePlayer(notes, 0, 5, 100);
		NoteLineView nlv = new NoteLineView(nlp, 1000, 400);
		for(int i = 0; i < notes.getNumBeats(); i++){
			int value = i%11;
			notes.setNoteValue( value, i);
			System.out.println("note index " + i + " is set to " + value);
		}
		Waypoints wp = new Waypoints(nlv, 5);
		wp.print();
	}
	
	
	//For now, make the waypoints straight lines from position to position. Each height is given in units of note heights, i.e. 1 is equal to the height of 1 blue box.
	private void initiateSteps(NoteLine notes){
		steps = new float[notes.getNumBeats() * numberOfStepsPerNote];
		
		for(int noteIndex = 0; noteIndex < notes.getNumBeats() ; noteIndex++){
			double currentHeight = (double) notes.getNote(noteIndex).getValue();
			double nextHeight =  (noteIndex + 1 >= notes.getNumBeats()) ? 0 : (double) notes.getNote(noteIndex + 1).getValue();
			
			makeStraightTransition(steps, numberOfStepsPerNote * noteIndex, numberOfStepsPerNote * (noteIndex + 1) - 1, currentHeight, nextHeight);
			
		}
	}
	
	/**
	 * @param steps The array into which the transition should be put
	 * @param fromIndex The first index number of the transition. Inclusive. 
	 * @param toIndex The last index number of the transition. Inclusive.
	 * @param fromValue The beginning value of the transition.
	 * @param toValue Then end value of the transition.
	 */
	private void makeStraightTransition(float [] steps, int fromIndex, int toIndex, double fromValue, double toValue){
		double progress = 0.0;
		
		for(int i = fromIndex; i <= toIndex; i++){				
			steps[i] = (float) MathUtils.getValueOnLine(progress, 0.0, (double)fromValue, 1.0, (double)toValue);
			progress += 1.0 / (double)(toIndex - fromIndex + 1);				
		}
	}
	
	/**
	 * @param steps The number of samples from the curve - the resolution of the curve.
	 * @return List of y-values of length steps. Equal step length for all steps.
	 */
	public byte[] getNextCurve(int steps){
		return null;
	}
	
	/**
	 * @param startY The initial y value. x = 0.
	 * @param weightX The x value of the weight point that defines the curvature. 
	 * @param weightY The y value of the weight point that defines the curvature.
	 * @param endX The x value of the ending point.
	 * @param endY The y value of the ending point.
	 * @return List of y-values of length steps. Equal step length for all steps.
	 */
	private byte[] getCurve(int startY, int weightX, int weightY, int endX, int endY, int steps){
		return null;
	}
	
	/**
	 * @return The next waypoint step. The unit is Note heights. If there are no more steps, it returns -1.
	 */
	public float getNextStep(){
		if(currentStep < steps.length)
			return steps[currentStep++];
		else
			return -1.0f;
	}
	
	public float getNextYCoordinateRelativeToNoteLineView(){
		float nextStep = getNextStep();
		return getYValueInNoteLineView(nextStep);
	}
	
	private float getYValueInNoteLineView(float stepValue){
		float cellHeight = noteLineView.getCellDimensions().y; 
		float viewHeight = noteLineView.getDimensions().y;
		return (viewHeight - stepValue * cellHeight);
	}
	
	
	public void print(){
		System.out.println("Waypoint:");
		for(int i = 0; i < steps.length; i++){
			System.out.println("Index " + i + ": " + steps[i] + " y-value: " + getYValueInNoteLineView(steps[i]));
		}
	}
	

}
