package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import dk.aau.oose.noteline.NoteLineView;

public class Runner extends PlaybackIndicator{
	
	private Animation anim;
	public Runner(NoteLineView nlv){
		super(nlv);
		try {
			anim = new Animation(new Image[]{
					new Image("assets/runner/01.png"),
					new Image("assets/runner/02.png"),
					new Image("assets/runner/03.png"),
					new Image("assets/runner/04.png"),
					new Image("assets/runner/05.png"),
					new Image("assets/runner/06.png"),
					new Image("assets/runner/07.png"),
					new Image("assets/runner/07_5.png"),
					new Image("assets/runner/08.png"),
					new Image("assets/runner/09.png"),
			}, 90);
		} catch (SlickException e) {
			e.printStackTrace();
			System.exit(1);
		}
		setBounds(0f, 0f, anim.getWidth(), anim.getHeight());
		drawingOffset = new Vector2f( - anim.getWidth() / 2, - anim.getHeight());
	}
	
	@Override
	public void onDraw(Graphics gfx) {
		anim.draw(drawingOffset.x, drawingOffset.y);
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
	
	public void stop(){
		try {
			anim = new Animation(new Image[]{
				new Image("assets/runner/stand.png")
			}, 10000);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		anim.stop();
		drawingOffset = new Vector2f( - anim.getWidth() / 4, - (anim.getHeight()));
	}
	
}
