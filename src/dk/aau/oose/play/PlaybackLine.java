package dk.aau.oose.play;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.noteline.NoteLineView;

public class PlaybackLine extends PlaybackIndicator {
	
	private Color color;
	
	public PlaybackLine(NoteLineView nlv){
		this(nlv, Color.red);
	}
	
	public PlaybackLine(NoteLineView nlv, Color color){
		super(nlv);
		this.color = color;
	}
	
	public void onUpdate(){
		
	}
	
	public void onDraw(Graphics gfx){
		gfx.setColor(color);
		gfx.fillRect(-1, 0, 3, (float) noteLineView.getBounds().getHeight());
	}

	@Override
	public void move(double trackProgress) {
		Vector2f newPos = waypoint.getNextStepRelativeToNoteLineView(trackProgress);
		setPosition(newPos.x, getPosition().y);
	}
	
}
