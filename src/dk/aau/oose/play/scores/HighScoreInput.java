package dk.aau.oose.play.scores;

import java.awt.geom.Rectangle2D;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.TextFieldElement;

public class HighScoreInput extends GameElement {
	
	//TODO make this shit work.
	
	private int score;
	private String name;
	private HighScoreManager hsm = HighScoreManager.getInstance();
	private HighScoreView hsv = new HighScoreView();
	private TextField tf;
	private TextFieldElement tfe;
	private Button button;
	private Button closeBtn;
	//Also show score
	
	public HighScoreInput(int score){
		this.score = score;
		tfe = new TextFieldElement(getGameContainer().getDefaultFont(), 130, 20);
		tf = tfe.getTextField();
		tf.setText("Enter name");
		tf.setBackgroundColor(Color.gray.darker());
		tf.setTextColor(Color.white);
		tf.setFocus(true);		
		/*
		txt.setAcceptingInput(true);
		txt.setCursorVisible(true);
		txt.setConsumeEvents(false);
		txt.setFocus(true);
		txt.setMaxLength(10);
		*/
		
		button = new Button(" Submit score", 130, 20){
			
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
				
		hsv.setPosition(0, 0);
		hsv.setBounds(130, 150);
		this.addChild(hsv);
		
		tfe.setPosition(0, 160);
		this.addChild(tfe);
		
		button.setPosition(0f, 190f);
		this.addChild(button);
		
		closeBtn = new Button("X", 20, 20){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				if(this.hitTestPoint(this.globalToLocal(x, y))){
					if(HighScoreInput.this.getParent() != null){
						HighScoreInput.this.destroy();
					}
				}
			}
			
		};
		closeBtn.setPosition(hsv.getWidth() - 20, hsv.getY() - 30);
		this.addChild(closeBtn);

		Rectangle2D bounds = getBounds();
		
		for(int i = 0; i < numChildren(); i++){
			Rectangle2D.union(bounds, getChildAt(i).getBounds(), bounds);
		}
		
		
	}

	@Override
	public void onDraw(Graphics g) {
		
		
	}
	
	public void submit(){
		System.out.println("Submitting name: " + tf.getText() + " and score: " + score );
		hsm.add(tf.getText(), score);
	}
	
	
	
}

