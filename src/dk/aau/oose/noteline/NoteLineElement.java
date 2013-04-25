package dk.aau.oose.noteline;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.AGameElement;
import dk.aau.oose.ITransform;
import dk.aau.oose.core.GameWorld;

public class NoteLineElement extends AGameElement implements ITransform {
	
	private Vector2f pos = new Vector2f(0, 0);
	private Vector2f dim;
	private Vector2f cellDim;
	private NoteLinePlayer nlp;
	private NoteLine nl;
	private Graphics gfx;
	private Color color = Color.white;
	
	
	public NoteLineElement(NoteLinePlayer nlp, float width, float height){
		this.nlp = nlp;
		this.nl = nlp.getNoteLine();
		dim = new Vector2f(width, height);
		cellDim = new Vector2f();
		cellDim.x = (float) width / nl.getNumBeats();
		cellDim.y = (float) height / nl.getMaxNote();
	}
	
	@Override
	public void draw() {
		gfx = GameWorld.getGameContainer().getGraphics();
		float height = dim.y;
		gfx.pushTransform();
		gfx.translate(pos.x, pos.y);
		gfx.setColor(color);
		for(int i = 0; i < nl.getNumBeats(); i++){
			int val = nl.getNote(i);
			if(val > 0){
				float xpos = cellDim.x * i;
				float ypos = height - cellDim.y * val;
				gfx.fillRect(xpos, ypos, cellDim.x, height);
			}
		}
		gfx.popTransform();
		
	}
	
	public int getNoteIndex(float x){
		int index = (int) ((x - pos.x) / cellDim.x);
		return index;
	}
	
	@Override
	public void update() {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mx = input.getMouseX();
			int index = getNoteIndex(mx);
			nlp.playNoteAt(index);
			System.out.println("Index: " + index);
		}
		
	}

	@Override
	public Vector2f getPosition() {
		return pos;
	}

	@Override
	public void setPosition(Vector2f pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}
	
	public void setColor(Color col){
		this.color = col;
	}

}
