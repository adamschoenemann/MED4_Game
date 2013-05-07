package dk.aau.oose.tests;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.util.MathUtils;

public class TestBezierCurve extends BasicGame {

    /** The container holding this test */
    private AppGameContainer app;
    /** The lines to be drawn on the screen */
    private ArrayList<Vector2f> lines = new ArrayList<Vector2f>();
    private Vector2f[] points;
    
    private Vector2f a = new Vector2f(0, 600);
    private Vector2f b = new Vector2f(400, 300);
    private Vector2f weight = new Vector2f(400, 0);
    
	public TestBezierCurve(int resolution){
		super("BezierCurve Test");
		points = new Vector2f[resolution];
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {
		
		g.setColor(Color.green);
		for(int i = 0; i < points.length - 1; i++){
			g.drawLine(points[i].x, points[i].y, points[i+1].x, points[i+1].y);
		}
		g.drawLine(points[points.length-1].x, points[points.length-1].y, b.x, b.y);
		
		g.setColor(Color.cyan);
		g.drawOval(weight.x - 3, weight.y - 3, 6, 6);
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		updateLine();
	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
		//if(container.getInput().isMouseButtonDown(0)){
			weight.x = container.getInput().getMouseX();
			weight.y = container.getInput().getMouseY();
			
			updateLine();
		//}
	}
	
	
	private void updateLine(){
		for(int i = 0; i < points.length; i++){
			float progress = (float)i / (float)points.length;
			points[i] = MathUtils.getPointOnBezierCurve(a, b, weight, progress);
		}
	}
	
    
    
    
    
    /**
     * Entry point to our test
     * 
     * @param argv The arguments passed into our test
     */
    public static void main(String[] argv) {
            try {
                    AppGameContainer container = new AppGameContainer(new TestBezierCurve(20));
                    container.setDisplayMode(800,600,false);
                    container.start();
            } catch (SlickException e) {
                    e.printStackTrace();
            }
    }

}
