package dk.aau.oose.tests;



import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.LinearLayout;
import dk.aau.oose.osc.MaxMSP;

public class TestLinearLayout extends BasicGame {

	public GameElement root;
	public LinearLayout layout;
	
	public TestLinearLayout() {
		super("TestLinearLayout");
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		GameElement.setGameContainer(gc);
		root = new GameElement();
		root.setBounds(gc.getWidth(), gc.getHeight());
		layout = new LinearLayout(LinearLayout.VERTICAL);
		root.addChild(layout);
		
		GameElement ele1 = new GameElement(){
			
			@Override
			public void onDraw(Graphics gfx){
				gfx.setColor(Color.red);
				gfx.drawRect(0, 0, 50, 100);
			}
			
		};
		
		ele1.setBounds(50, 100);
		
		Button ele2 = new Button("Button1", 400, 20);
		Button ele3 = new Button("Button2", 100, 40);
		layout.addChild(ele1);
		layout.addChild(ele2);
		layout.addChild(ele3);
		
		layout.center(true, true);
		System.out.format("Position: (%f, %f)\n", layout.getX(), layout.getY());
		System.out.println("Bounds: " + layout.getBounds());
		
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		root.draw();
		
	}

	

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		MaxMSP.Connect("127.0.0.1", 7400);
		try {
			AppGameContainer container = new AppGameContainer(new TestLinearLayout());
			container.setDisplayMode(1000,800,false);
			container.setMinimumLogicUpdateInterval(20);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
}
