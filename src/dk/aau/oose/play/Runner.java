package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.noteline.NoteLineView;

public class Runner extends PlaybackIndicator{
	
	private Image sprite;
	public Runner(NoteLineView nlv){
		super(nlv);
		try {
			//TODO use proper animation
			sprite = new Image("assets/ship.png"); // new Image("assets/runner/02.png"); //
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		setDimensions(sprite.getWidth(), sprite.getHeight());
		drawingOffset = new Vector2f( - sprite.getWidth() / 2, - sprite.getHeight());
		
		this.setPosition( new Vector2f(noteLineView.getDimensions().x * 0.5f, noteLineView.getDimensions().y)); //Set in centre of track, at the bottom line.
	}
	
	@Override
	public void onDraw(Graphics gfx) {
		sprite.draw(drawingOffset.x, drawingOffset.y);
	}

	public void setNextJumpIsGood(boolean nextJumpIsGood) {
		System.out.println("setNextJumpIsGood needs an implementation!");
		//this.nextJumpIsGood = nextJumpIsGood;
	}

	@Override
	public void move(double trackProgress) {
		Vector2f newPos = waypoint.getNextStepRelativeToNoteLineView(trackProgress);
		setPosition(newPos);
	}
	
}
