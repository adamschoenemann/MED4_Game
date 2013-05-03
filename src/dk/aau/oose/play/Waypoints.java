package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;

import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.MathUtils;


/**
 * 
 * @author Thorbjorn
 *	Provides a nice path for the PlaybackIndicators. 
 *
 */
public class Waypoints {
	
	private NoteLineView noteLineView;
	private int numberOfStepsPerNote;
	private float steps[];
	private float horizontalOffset;
	private int introLength;
	
	
	public Waypoints(NoteLineView noteLineView, int numberOfStepsPerNote){
		this.noteLineView = noteLineView;
		this.numberOfStepsPerNote = numberOfStepsPerNote;
		initiateSteps(noteLineView.getNoteLinePlayer().getNoteLine()); 
		horizontalOffset = 0.0f;//noteLineView.getCellDimensions().x / 2.0f;
	}
	
	
	private void initiateSteps(NoteLine notes){
		introLength = PlayThread.NUMBER_OF_COUNTIN_CLICKS * numberOfStepsPerNote;
		int jumpToFirstNoteStart = (PlayThread.NUMBER_OF_COUNTIN_CLICKS - 1) * numberOfStepsPerNote;
		
		steps = new float[introLength + notes.getNumBeats() * numberOfStepsPerNote + 1];
		
		//Set up the intro part
		for(int i = 0; i < jumpToFirstNoteStart; i++){
			steps[i] = 0.0f;
		}

		makeBezierTransition(steps, jumpToFirstNoteStart, introLength - 1, 0.0, (double) notes.getNote(0).getValue());
		
		//Set up waypoints for the main track
		for(int noteIndex = 0; noteIndex < notes.getNumBeats() ; noteIndex++){
			double currentHeight = (double) notes.getNote(noteIndex).getValue();
			double nextHeight =  (noteIndex == notes.getNumBeats() - 1) ? 0.0 : (double) notes.getNote(noteIndex + 1).getValue();
			
			int fromIndex = numberOfStepsPerNote * noteIndex + introLength;
			int toIndex = numberOfStepsPerNote * (noteIndex + 1) - 1 + introLength;

			makeBezierTransition(steps, fromIndex, toIndex, currentHeight, nextHeight);
			//makeStraightTransition(steps, numberOfStepsPerNote * noteIndex, numberOfStepsPerNote * (noteIndex + 1) - 1, currentHeight, nextHeight);
		}
		steps[steps.length - 1] = 0.0f;
	}
	
	/*
	 * @param steps The array into which the transition should be put
	 * @param fromIndex The first index number of the transition. Inclusive. 
	 * @param toIndex The last index number of the transition. Inclusive.
	 * @param fromValue The beginning value of the transition.
	 * @param toValue Then end value of the transition.
	 *
	private void makeStraightTransition(float [] steps, int fromIndex, int toIndex, double fromValue, double toValue){
		double progress = 0.0;
		
		for(int i = fromIndex; i <= toIndex; i++){				
			steps[i] = (float) MathUtils.getValueOnLine(progress, 0.0, (double)fromValue, 1.0, (double)toValue);
			progress += 1.0 / (double)(toIndex - fromIndex + 1);				
		}
	}*/
	
	
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
			steps[i] = (float) MathUtils.getPointOnBezierCurve(a, b, weight, progress).y;
			progress += 1.0 / (float)(toIndex - fromIndex + 1);				
		}
	}
	
	
	/**
	 * @param progress [0;1[
	 * @return the waypoint that is progress down the route.
	 */
	public Vector2f getNextStepRelativeToNoteLineView(double progress){
		int index;
		if(progress < 0.0){
			index =  (int)Math.round(MathUtils.scale(progress, -1.0, 0.0, 0.0, (double)introLength));
		} else {
			index = (int)Math.round(MathUtils.scale(progress, 0.0, 1.0, introLength, steps.length));
		}

		index = MathUtils.clip(index, 0, steps.length - 1);
		
		return stepToNoteLineView(index);
	}
	
	/**
	 * @param step A step index.
	 * @return The vector from this instance's noteLineView's origin to the position of the step & step value. 
	 */
	public Vector2f stepToNoteLineView(int step){

		float x = ((float)(step - introLength)/numberOfStepsPerNote) * noteLineView.getCellDimensions().x + horizontalOffset;
		float y = noteLineView.getBounds().y - steps[step] * noteLineView.getCellDimensions().y;
		
		return new Vector2f(x, y);
	}
	
	public float[] getSteps(){
		return steps.clone();
	}
	
	public void printSteps(){
		for(int i = 0; i < steps.length; i++){
			System.out.println("i: " + i + "\tstep: " + steps[i]);
		}
	}
}
