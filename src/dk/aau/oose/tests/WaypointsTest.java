package dk.aau.oose.tests;



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

import dk.aau.oose.core.GameWorld;
import dk.aau.oose.util.MathUtils;


public class WaypointsTest extends BasicGame{


	/** The container holding this test */
	private AppGameContainer app;



	public WaypointsTest() {
		super("Waypoints Test");
	}

	@Override
	public void render(GameContainer arg0, Graphics g) throws SlickException {

		


	}

	@Override
	public void init(GameContainer arg0) throws SlickException {

	}

	@Override
	public void update(GameContainer container, int arg1) throws SlickException {
		

	}


	private void updateLine(){
		

	}



	public static void main(String[] argv) {
		try {
			AppGameContainer container = new AppGameContainer(new BezierCurveTest(20));
			container.setDisplayMode(800,600,false);
			container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}




}
