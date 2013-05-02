package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;

public abstract class PlaybackIndicator extends GameElement {

	protected Waypoints waypoint;
	protected Vector2f drawingOffset;
	protected NoteLineView noteLineView;

	public PlaybackIndicator(NoteLineView nlv) {
		noteLineView = nlv;
		updateWaypoints();
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	abstract public void move(double trackProgress); 

	
	/*
	 * Moves the runner to its next position.
	 *
	public void moveHorizontal(double trackProgress) {
		Vector2f newPos = waypoint.getNextStepRelativeToNoteLineView(trackProgress);
		setPosition(newPos.x, getPosition().y);
	}

	public void move2D(double trackProgress) {
		Vector2f newPos = waypoint.getNextStepRelativeToNoteLineView(trackProgress);
		setPosition(newPos);
	}
*/
	public NoteLineView getNoteLineView() {
		return noteLineView;
	}
	
	public void updateWaypoints(){
		waypoint = new Waypoints(noteLineView, 8); //Fixed resolution at 8 steps per beat
	}

}