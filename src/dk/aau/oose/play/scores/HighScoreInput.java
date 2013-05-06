package dk.aau.oose.play.scores;

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
	
	private TextField txt;
	private AButton button;
	//Also show score
	
	public HighScoreInput(int score){
		this.score = score;
		txt = new TextField(getGameContainer(), getGameContainer().getDefaultFont(), 0, 0, 100, 20);
		txt.setText("Enter name");
		txt.setBackgroundColor(Color.gray.darker());
		txt.setTextColor(Color.white);
		txt.setAcceptingInput(true);
		txt.setCursorVisible(true);
		txt.setConsumeEvents(false);
		txt.setFocus(true);
		txt.setMaxLength(10);
		
		button = new AButton(" Submit score", 130, 20){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(x, y)){
						submit();
					}
				}
			}
		};
		button.setPosition(0.0f, 50.0f);
	}

	@Override
	public void onDraw(Graphics g) {
		//txt.render(getGameContainer(), g);
		button.draw();
	}
	
	private void submit(){
		System.out.println("Submitting name: " + txt.getText() + " and score: " + score );
	}
	
	
	
}
