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
		move(-1.0);
	}
	
	abstract public void move(double trackProgress); 

	public NoteLineView getNoteLineView() {
		return noteLineView;
	}
	
	public void updateWaypoints(){
		waypoint = new Waypoints(noteLineView, 20); //Fixed resolution at 8 steps per beat
	}

}