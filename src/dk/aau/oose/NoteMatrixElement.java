package dk.aau.oose;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameWorld;

public class NoteMatrixElement extends AGameElement {
	
	private final NoteMatrix nm;
	private final int width, height, colWidth, rowHeight;
	private final Graphics gfx = GameWorld.getGameContainer().getGraphics();
	private static final Color[] colors = {
		Color.black,
		Color.blue.darker(0.6f),
		Color.blue.darker(0.3f), 
		Color.blue
		};
	
	/**
	 * 
	 * @param nm - NoteMatrix
	 * @param width
	 * @param height
	 */
	public NoteMatrixElement(NoteMatrix nm, int width, int height){
		this.nm = nm;
		this.width = width;
		this.height = height;
		this.rowHeight = height / nm.getRows();
		this.colWidth = width / nm.getColumns();
	}
	
	
	// FIXME: Set alpha based on octave. Right now its not working
	@Override
	public void draw() {
		for(int rows = 0; rows < nm.getRows(); rows++){
			for(int cols = 0; cols < nm.getColumns(); cols++){
				int note = nm.getNote(rows, cols);
				gfx.setColor(colors[note]);
				int startX = cols * colWidth;
				int startY = rows * rowHeight;
				gfx.fillRect(startX, startY, startX + colWidth, startY + rowHeight);
			}
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
