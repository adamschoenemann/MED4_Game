package dk.aau.oose.notmat;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.AGameElement;
import dk.aau.oose.ITransform;
import dk.aau.oose.core.GameWorld;

public class NoteMatrixElement extends AGameElement implements ITransform {
	
	private final NoteMatrix nm;
	private final NoteMatrixPlayer nmp;
	private final int width, height, colWidth, rowHeight;
	private final Graphics gfx = GameWorld.getGameContainer().getGraphics();
	private static final Color[] colors = {
		Color.black,
		Color.blue.darker(0.6f),
		Color.blue.darker(0.3f), 
		Color.blue
		};
	private Vector2f pos;
	
	/**
	 * 
	 * @param nm - NoteMatrix
	 * @param width
	 * @param height
	 */
	public NoteMatrixElement(NoteMatrix nm, int width, int height){
		this.nm = nm;
		this.nmp = new NoteMatrixPlayer(nm, 180);
		this.width = width;
		this.height = height;
		this.rowHeight = height / nm.getRows();
		this.colWidth = width / nm.getColumns();
		this.pos = new Vector2f(0, 0);
	}
	
	@Override
	public void draw() {
		gfx.pushTransform();
		gfx.translate(pos.x, pos.y);
		for(int rows = 0; rows < nm.getRows(); rows++){
			for(int cols = 0; cols < nm.getColumns(); cols++){
				int note = nm.getNote(rows, cols);
				gfx.setColor(colors[note]);
				int startX = cols * colWidth;
				int startY = rows * rowHeight;
				gfx.fillRect(startX, startY, startX + colWidth, startY + rowHeight);
			}
		}
		gfx.setColor(Color.black);
		gfx.popTransform();
	}

	@Override
	public void update() {
		Input input = GameWorld.getGameContainer().getInput();

		try {
			
			
			if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
				// Edit notes
				if(input.isKeyDown(Input.KEY_LSHIFT)){
					int mx = input.getMouseX(), my = input.getMouseY();
					int note = getNoteAtPos(mx, my);
					note = ++note % (NoteMatrix.OCTAVES + 1);
					setNoteAtPos(mx, my, note);
				}
				// Play columns
				else {
					int mx = input.getMouseX();
					int colIndex = getColumnIndex(mx);
					nmp.playColumnAt(colIndex);
				}
			}
							
			// Play notes
			if(input.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){
				int mx = input.getMouseX(), my = input.getMouseY();
				playNoteAtPos(new Vector2f(mx, my));
			}
			
		} catch (IndexOutOfBoundsException e){
			e.printStackTrace();
			return;
		}
		
	}
	
	public int getColumnIndex(float x){
		int colIndex = (int) Math.floor((x - pos.x) / colWidth);
		// Out of bounds
		if(colIndex < 0 || colIndex >= nm.getColumns()){
			throw new IndexOutOfBoundsException("Invalid x coordinates given");
		}
		return colIndex;
	}
	
	public int getRowIndex(float y){
		int rowIndex = (int) Math.floor((y - this.pos.y) / rowHeight);
		// Out of bounds
		if(rowIndex < 0 || rowIndex >= nm.getRows()){
			throw new IndexOutOfBoundsException("Invalid y coordinates given");
		}
		return rowIndex;
	}
	
	
	public void playNoteAtPos(float x, float y){
		int row = getRowIndex(y);
		int col = getColumnIndex(x);
		nmp.playNoteAt(row, col);
	}
	
	public void playNoteAtPos(Vector2f position){
		playNoteAtPos(position.x, position.y);
	}
	
	public int getNoteAtPos(int x, int y){
		int row = getRowIndex(y);
		int col = getColumnIndex(x);
		return nm.getNote(row, col);
	}
	
	public void setNoteAtPos(int x, int y, int val){
		int row = getRowIndex(y);
		int col = getColumnIndex(x);
		System.out.format("Setting note at [%d, %d] to value %d\n", row, col, val);
		if(val > NoteMatrix.OCTAVES){
			throw new RuntimeException("Note octave above max value");
		}
		nm.setNote(row, col, val);
	}
	
	public NoteMatrixPlayer getPlayer(){
		return nmp;
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
	
	public void setPosition(float x, float y){
		pos.x = x;
		pos.y = y;
	}
	
	
	
}
