package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.util.Vec;

public class Runner extends GameElement{
	
	private Image sprite;
	private Waypoints waypoint;
	private boolean nextJumpIsGood;
	private Vector2f drawingOffset;

	public Runner(NoteLineView notes){
		
		try {
			sprite = new Image("assets/runner/02.png");
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(1);
		}
		drawingOffset = new Vector2f( - sprite.getWidth() / 2, - sprite.getHeight());
		waypoint = new Waypoints(notes, 5);
		this.setPosition( new Vector2f(notes.getDimensions().x * 0.5f, notes.getDimensions().y)); //Set in centre of track, at the bottom line.
		
	}
	
	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		sprite.draw(drawingOffset.x, drawingOffset.y);
	}

	public void setNextJumpIsGood(boolean nextJumpIsGood) {
		this.nextJumpIsGood = nextJumpIsGood;
	}
	
	public void jump(){
		
	}
	
	/**
	 * Moves the runner to its next position.
	 */
	public void moveOneStep(){
		this.setPosition(new Vector2f(this.getPosition().x, waypoint.getNextYCoordinateRelativeToNoteLineView()));
	}
	
}
