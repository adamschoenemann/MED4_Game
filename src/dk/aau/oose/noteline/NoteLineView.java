package dk.aau.oose.noteline;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.Grid;
import dk.aau.oose.core.GameElement;

public class NoteLineView extends GameElement {
	
	private static final float X_PADDING = 5f;
	private Vector2f cellDim;
	private NoteLinePlayer nlp;
	private NoteLine nl;
	private Color color = Color.white;
	private final Grid grid;
	private boolean showGrid = true;
	
	/**
	 * @param nlp
	 * @param width The total width of this view, in pixels.
	 * @param height The total height of this view, in pixels.
	 */
	public NoteLineView(NoteLinePlayer nlp, float width, float height){
		this.nlp = nlp;
		this.nl = nlp.getNoteLine();
		setBounds(0, 0, width, height);
		cellDim = new Vector2f();
		cellDim.x = (float) width / nl.getNumBeats();
		cellDim.y = (float) height / nl.getMaxNote();
		grid = new Grid(width, height, nl.getNumBeats(), nl.getMaxNote());
		grid.setBackgroundColor(Color.transparent);
		grid.setForegroundColor(new Color(255, 255, 255, 127));
	}
	
	@Override
	public void onDraw(Graphics gfx) {

		float height = getBounds().height;

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
				gfx.fillRect(xpos, ypos, cellWidth, height - ypos);
			}
		}
		if(showGrid)
			grid.draw();
		
	}
	
	public int calculateNoteHeight(float y){
		float globalY = localToGlobal(0.0f, getPosition().y).y;
		int height = (int) Math.floor((y - globalY) / cellDim.y);
		if(height > nl.getMaxNote() || height < 0)
			return -1;
		else
			return nl.getMaxNote() - height;
	}
	
	public int calculateNoteIndex(float x){
		float globalX = localToGlobal(getPosition().x, 0.0f).x;
		int index = (int) ((x - globalX) / cellDim.x); 
		if(index < 0 || index >= nl.getNumBeats())
			return -1;
		else
			return index;
	}
	
	@Override
	public void onUpdate() {
		
	}
	
	public NoteLinePlayer getNoteLinePlayer(){
		return nlp;
	}
	
	public void setColor(Color col){
		this.color = col;
	}

	public Vector2f getCellDimensions() {
		return new Vector2f(cellDim);
	}
	
	public static NoteLineView newTestInstance(int steps, int numBeats, int startOctave, int notesPerOctave, int tempo, float width, float height){
		NoteLine nl = NoteLine.newTestInstance(steps, numBeats);
		NoteLinePlayer nlp = new NoteLinePlayer(nl, startOctave, notesPerOctave, tempo);
		NoteLineView nlv = new NoteLineView(nlp, width, height);
		
		return nlv;
	}
	
	public static NoteLineView newEmptyInstance(int steps, int numBeats, int startOctave, int notesPerOctave, int tempo, float width, float height){	
		return newFixedValueInstance(0, steps, numBeats, startOctave, notesPerOctave, tempo, width, height);
	}
	
	public static NoteLineView newFixedValueInstance(int value, int steps, int numBeats, int startOctave, int notesPerOctave, int tempo, float width, float height){
		NoteLine nl = NoteLine.newFixedValueInstance(value, steps, numBeats);
		NoteLinePlayer nlp = new NoteLinePlayer(nl, startOctave, notesPerOctave, tempo);
		NoteLineView nlv = new NoteLineView(nlp, width, height);
		
		return nlv;
	}
	
	public void setShowGrid(boolean showGrid){
		this.showGrid = showGrid;
	}
	
	

}
