package dk.aau.oose;



import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameWorld;

public class Grid extends AGameElement {
	
	private final float width, height;
	private final int cols, rows;
	private final float cellWidth, cellHeight;
	private final Graphics gfx = GameWorld.getGameContainer().getGraphics();
	private Color bgColor = Color.white;
	private Color fgColor = Color.black;
	
	/**
	 * Grid Constructor. Specify width, height, columns and rows
	 * @param width2 - the width of the grid
	 * @param height2 - the height
	 * @param columns - number of columns
	 * @param rows - number of rows
	 */
	
	public Grid(float width, float height, int cols, int rows){
		this.width = width;
		this.height = height;
		this.cols = cols;
		this.rows = rows;
		this.cellWidth = width / (float) cols;
		this.cellHeight = height / (float) rows;
	}
	
	public void setForegroundColor(Color col){
		fgColor = col;
	}
	
	public void setBackgroundColor(Color col){
		bgColor = col;
	}
	
	@Override
	public void draw() {
		gfx.setColor(bgColor);
		gfx.fillRect(0, 0, width, height);
		gfx.setColor(fgColor);
		for(int r = 0; r < rows; r++){
			gfx.drawLine(0, r * cellHeight, width, r * cellHeight);
		}
		for(int c = 0; c < cols; c++){
			gfx.drawLine(c * cellWidth, 0, c * cellWidth, height);
		}
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
