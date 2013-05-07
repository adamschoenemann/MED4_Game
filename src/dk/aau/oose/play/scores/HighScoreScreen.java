package dk.aau.oose.play.scores;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.LinearLayout;

public class HighScoreScreen extends GameElement {
	
	private Button closeBtn;
	private LinearLayout container;
	
	public HighScoreScreen(){
		this.setBounds(getGameContainer().getWidth(), getGameContainer().getHeight());
		closeBtn = new Button("X", 20, 20){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				if(this.hitTestPoint(globalToLocal(x, y))){
					if(HighScoreScreen.this.getParent() != null) {
						HighScoreScreen.this.destroy();
					}
				}
			}
			
		};
		
		container = new LinearLayout();
		
		this.addChild(closeBtn);
		this.addChild(container);
	}
	
	
	@Override
	public void onDraw(Graphics g){
		g.setColor(Color.black);
		g.fillRect(getBounds().x, getBounds().y, getWidth(), getHeight());
	}
	
	public void addHighScoreInput(HighScoreInput hsi){
		container.addChild(hsi);
		container.center(true, true);
	}

}
