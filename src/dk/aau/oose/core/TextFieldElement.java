package dk.aau.oose.core;



import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.TextField;

public class TextFieldElement extends GameElement {

	private String text = "";
	private Color txtColor = Color.black;
	private Color bgColor = Color.white;
	
	
	public TextFieldElement(String txt){
		setText(txt);
	}
	
	public void setText(String txt){
		text = txt;
	}
	
	public String getText(){
		return text;
	}
	
	@Override
	public void onDraw(Graphics g){
		g.setColor(bgColor);
		g.drawRoundRect(getBounds().x, getBounds().y, getWidth(), getHeight(), 5);
		g.setColor(txtColor);
		g.drawString(text, getBounds().x, getBounds().y);
		
	}
}
