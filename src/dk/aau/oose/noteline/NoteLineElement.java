package dk.aau.oose.noteline;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.AGameElement;
import dk.aau.oose.Grid;
import dk.aau.oose.ITransform;
import dk.aau.oose.core.GameWorld;

public class NoteLineElement extends AGameElement {
	
	private static final float X_PADDING = 5f;
	private Vector2f cellDim;
	private NoteLinePlayer nlp;
	private NoteLine nl;
	private Graphics gfx;
	private Color color = Color.white;
	private final Grid grid;
	
	
	public NoteLineElement(NoteLinePlayer nlp, float width, float height){
		this.nlp = nlp;
		this.nl = nlp.getNoteLine();
		setDimensions(new Vector2f(width, height));
		cellDim = new Vector2f();
		cellDim.x = (float) width / nl.getNumBeats();
		cellDim.y = (float) height / nl.getMaxNote();
		grid = new Grid(width, height, nl.getNumBeats(), nl.getMaxNote());
		grid.setBackgroundColor(Color.transparent);
		grid.setForegroundColor(new Color(255, 255, 255, 127));
	}
	
	@Override
	public void draw() {
		gfx = GameWorld.getGameContainer().getGraphics();
		float height = getDimensions().y;
		gfx.pushTransform();
		gfx.translate(getPosition().x, getPosition().y);
		gfx.setColor(color);
		for(int i = 0; i < nl.getNumBeats(); i++){
			int val = nl.getNote(i).getValue();
			if(val > 0){
				float xpos = cellDim.x * i;
				float ypos = height - cellDim.y * val;
				
				float cellWidth = cellDim.x;
				if(nl.getNote(i).isDistinct() == true){
					cellWidth -= X_PADDING;
				}
				gfx.fillRect(xpos, ypos, cellWidth, height);
			}
		}
		grid.draw();
		gfx.popTransform();
		
	}
	
	public int calculateNoteHeight(float y){
		int height = (int) Math.floor((y - getPosition().y) / cellDim.y);
		return nl.getMaxNote() - height;
	}
	
	public int calculateNoteIndex(float x){
		int index = (int) ((x - getPosition().x) / cellDim.x);
		return index;
	}
	
	@Override
	public void update() {
		Input input = GameWorld.getGameContainer().getInput();
		if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			float mx = input.getMouseX(), my = input.getMouseY();
			if(input.isKeyDown(Input.KEY_LSHIFT)){
				int noteHeight = calculateNoteHeight(my);
				int index = calculateNoteIndex(mx);
				nl.setNoteValue(noteHeight, index);
			} else {
				int index = calculateNoteIndex(mx);
				nlp.playNoteAt(index);
				System.out.println("Index: " + index);
			}
		}
		if(input.isKeyPressed(Input.KEY_SPACE)){
			nlp.play();
		}
	}
	
	public void setColor(Color col){
		this.color = col;
	}

}
