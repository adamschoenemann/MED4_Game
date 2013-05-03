package dk.aau.oose.tests;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.osc.MaxMSP;

public class CreateControllerTest extends BasicGame {

	private CreateController cc;
	
	public CreateControllerTest() {
		super("CreateController test");
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		cc.draw();

	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		cc = new CreateController();
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		cc.update();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {
			AppGameContainer container = new AppGameContainer(new CreateControllerTest());
			container.setDisplayMode(1000,800,false);
			container.setMinimumLogicUpdateInterval(20);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

}
