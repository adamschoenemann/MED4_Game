package dk.aau.oose;



import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;

public class Grid extends GameElement {
	
	private final float width, height;
	private final int cols, rows;
	private final float cellWidth, cellHeight;
	private Color bgColor = Color.white;
	private Color fgColor = Color.black;
	// FIXME: This should be made more customizable (getter/setter)
	private Vector2f superDivisions = new Vector2f(4f, 5f);
	private Color superColor = Color.white;
	
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
	
	public void setSuperDivisions(int cols, int rows){
		superDivisions.x = cols;
		superDivisions.y = rows;
	}
	
	public void setForegroundColor(Color col){
		fgColor = col;
	}
	
	public void setBackgroundColor(Color col){
		bgColor = col;
	}
	
	@Override
	public void onDraw(Graphics gfx) {
		gfx.setColor(bgColor);
		gfx.fillRect(0, 0, width, height);
		gfx.setColor(fgColor);
		
		for(int r = 0; r < rows; r++){
			gfx.setColor((superDivisions.y > 0 && (r % superDivisions.y == 0)) ? superColor : fgColor);
			gfx.drawLine(0, r * cellHeight, width, r * cellHeight);
		}
		for(int c = 0; c < cols; c++){
			gfx.setColor((superDivisions.x > 0 && (c % superDivisions.x == 0)) ? superColor : fgColor);
			gfx.drawLine(c * cellWidth, 0, c * cellWidth, height);
		}
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

}
