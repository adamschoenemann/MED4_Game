package dk.aau.oose.play.scores;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;

import dk.aau.oose.core.AButton;
import dk.aau.oose.core.GameElement;

public class HighScoreInput extends GameElement {
	
	//TODO make this shit work.
	
	private int score;
	private String name;
	private HighScoreManager hsm = HighScoreManager.getInstance();
	private HighScoreView hsv = new HighScoreView();
	private TextField txt;
	private AButton button;
	//Also show score
	
	public HighScoreInput(int score){
		this.score = score;
		txt = new TextField(getGameContainer(), getGameContainer().getDefaultFont(), 0, 0, 130, 20){
			
			@Override
			public void mouseReleased(int btn, int x, int y){
				
				if(hitTestPoint(globalToLocal(x, y))){
					System.out.println("mousePressed on TextField!");
					this.setFocus(true);
				} else {
					this.setFocus(false);
				}
				
			}
			
		};
		txt.setText("Enter name");
		txt.setBackgroundColor(Color.gray.darker());
		txt.setTextColor(Color.white);
		txt.setFocus(true);		
		/*
		txt.setAcceptingInput(true);
		txt.setCursorVisible(true);
		txt.setConsumeEvents(false);
		txt.setFocus(true);
		txt.setMaxLength(10);
		*/
		
		button = new AButton(" Submit score", 130, 20){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				Vector2f loc = this.globalToLocal(x, y);
				System.out.format("(%f, %f)\n", loc.x, loc.y);
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						submit();
					}
				}
			}
			
			@Override
			public void keyPressed(int btn, char c){
				if(btn == Input.KEY_ENTER){
					submit();
				}
			}
		};
		
		setBounds(130, 120);
		button.setPosition(0f, 30f);
		button.listen();
		this.addChild(button);
		
		hsv.setPosition(0, -170);
		hsv.setBounds(130, 150);
		this.addChild(hsv);
	}

	@Override
	public void onDraw(Graphics g) {
		g.setColor(Color.white);
		txt.render(getGameContainer(), g);
	}
	
	public void submit(){
		System.out.println("Submitting name: " + txt.getText() + " and score: " + score );
		hsm.add(txt.getText(), score);
	}
	
	
	
}

