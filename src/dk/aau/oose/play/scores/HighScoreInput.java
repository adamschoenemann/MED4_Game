package dk.aau.oose.play.scores;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoreInput extends Panel {
	
	private Button testbtn;
	
	public HighScoreInput(){
		testbtn = new Button("Test");
		add(testbtn);
		testbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				buttonPressed();
			}
		});
	}
	
	private void buttonPressed(){
		System.out.println("Button pressed");
	}
	
	
}



/*
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
				Vector2f loc = this.globalToLocal(x, y);
				System.out.format("(%f, %f)\n", loc.x, loc.y);
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						System.out.println("Hey!");
						submit();
					}
				}
			}
		};
		button.setPosition(0.0f, 50.0f);
		button.listen();
		this.addChild(button);
	}

	@Override
	public void onDraw(Graphics g) {
		txt.render(getGameContainer(), g);
		//button.draw();
	}
	
	public void submit(){
		System.out.println("Submitting name: " + txt.getText() + " and score: " + score );
	}
	
	
	
}

 * 
 * 
 */
