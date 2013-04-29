package dk.aau.oose.play;

import dk.aau.oose.noteline.NoteLine;

public class Waypoints {
	
	private NoteLine notes;
	private int numberOfStepsPerNote;
	private int currentNoteIndex = -1;
	
	public Waypoints(NoteLine notes, int numberOfStepsPerNote){
		this.notes = notes;
		this.numberOfStepsPerNote = numberOfStepsPerNote;
	}
	
	/**
	 * @param steps The number of samples from the curve - the resolution of the curve.
	 * @return List of y-values of length steps. Equal step length for all steps.
	 */
	public int[] getNextCurve(int steps){
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
	private int[] getCurve(int startY, int weightX, int weightY, int endX, int endY, int steps){
		return null;
	}
	

}
