package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;

import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.MathUtils;


/**
 * 
 * @author Thorbjorn
 *	Provides a nice path for the Runner. 
 *
 */
public class Waypoints {
	
	private NoteLineView noteLineView;
	private int numberOfStepsPerNote;
	private float steps[];
	private float horizontalOffset;
	//private int currentStep = 0;
	
	
	public Waypoints(NoteLineView noteLineView, int numberOfStepsPerNote){
		this.noteLineView = noteLineView;
		this.numberOfStepsPerNote = numberOfStepsPerNote;
		initiateSteps(noteLineView.getNoteLinePlayer().getNoteLine()); 
		horizontalOffset = noteLineView.getCellDimensions().x / 2.0f;
	}
	
	
	private void initiateSteps(NoteLine notes){
		steps = new float[notes.getNumBeats() * numberOfStepsPerNote];
		
		for(int noteIndex = 0; noteIndex < notes.getNumBeats() ; noteIndex++){
			double currentHeight = (double) notes.getNote(noteIndex).getValue();
			double nextHeight =  (noteIndex + 1 >= notes.getNumBeats()) ? 0 : (double) notes.getNote(noteIndex + 1).getValue();
			
			makeBezierTransition(steps, numberOfStepsPerNote * noteIndex, numberOfStepsPerNote * (noteIndex + 1) - 1, currentHeight, nextHeight);
			//makeStraightTransition(steps, numberOfStepsPerNote * noteIndex, numberOfStepsPerNote * (noteIndex + 1) - 1, currentHeight, nextHeight);
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
	 * @param steps The array into which the transition should be put
	 * @param fromIndex The first index number of the transition. Inclusive. 
	 * @param toIndex The last index number of the transition. Inclusive.
	 * @param fromValue The beginning value of the transition.
	 * @param toValue Then end value of the transition.
	 */
	private void makeBezierTransition(float [] steps, int fromIndex, int toIndex, double fromValue, double toValue){
		float progress = 0.0f;
		
		Vector2f a = new Vector2f((float)fromIndex, (float)fromValue);
		Vector2f b = new Vector2f((float)toIndex, (float)toValue);
		Vector2f weight = new Vector2f( b.x - a.x,     2.0f*Math.abs(b.y - a.y) + Math.min(a.y, b.y));
		
		if(a.y == b.y){
			weight.y = a.y + 0.75f;
		}
		
		for(int i = fromIndex; i <= toIndex; i++){				
			steps[i] = (float) MathUtils.getPointOnBezierCurve(a, b, weight, progress).y;// getValueOnLine(progress, 0.0, (double)fromValue, 1.0, (double)toValue);
			progress += 1.0 / (float)(toIndex - fromIndex + 1);				
		}
	}
	
	
	/**
	 * @param progress [0;1[
	 * @return the waypoint that is progress down the route.
	 */
	public Vector2f getNextStepRelativeToNoteLineView(double progress){
		int index = (int)Math.round(steps.length * progress);
		if(index >= steps.length)
			index = steps.length - 1;
		else if(index < 0)
			index = 0;
		return stepToNoteLineView(index);
	}
	
	/*
	public Vector2f getNextStepRelativeToNoteLineView(){
		Vector2f next = stepToNoteLineView(currentStep);
		if(currentStep + 1 < steps.length)
			currentStep++;
		return next;
	}*/
	
	/**
	 * @param step A step index.
	 * @return The vector from this instance's noteLineView's origin to the position of the step & step value. 
	 */
	public Vector2f stepToNoteLineView(int step){
		float x = ((float)step/numberOfStepsPerNote) * noteLineView.getCellDimensions().x + horizontalOffset;
		float y = noteLineView.getDimensions().y - steps[step] * noteLineView.getCellDimensions().y;
		
		return new Vector2f(x, y);
	}
	
	public float[] getSteps(){
		return steps.clone();
	}
}
