package dk.aau.oose.core;



import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;

public class TextFieldElement extends GameElement {

	private TextField tf;
	
	public TextFieldElement(Font font, int width, int height){
		tf = new TextField(getGameContainer(), font, 0, 0, width, height){
			
			@Override
			public void mouseReleased(int btn, int x, int y){
				//System.out.format("(%d, %d)\n", x, y);
				if(hitTestPoint(globalToLocal(x, y))){
					tf.setFocus(true);
				} else {
					tf.setFocus(false);
				}
			}
			
		};
		setBounds(width, height);
	}
	
	public void onDraw(Graphics gfx){
		gfx.setColor(Color.white);
		tf.render(getGameContainer(), gfx);
	}
	
	public TextField getTextField(){
		return tf;
	}
	
}
