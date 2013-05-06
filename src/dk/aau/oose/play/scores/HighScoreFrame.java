package dk.aau.oose.play.scores;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HighScoreFrame extends JFrame {
	
	private JPanel panel;
	
	public HighScoreFrame(){
		
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

//		
//		g.setColor(Color.black);
//		g.fillRect(0, 0, 400, 400);
	}
	
	@Override
	protected void frameInit() {
		super.frameInit();
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800, 600));
		this.setVisible(true);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.getContentPane().add(panel);
		JTextField tf = new JTextField("Enter name...");
		panel.add(tf);
		panel.add(new JTextField("Another test!"));
		this.pack();
	}

	@Override
	public void update(Graphics arg0) {
		super.update(arg0);
	}

	public static void main(String[] args){
		new HighScoreFrame();
	}
	
}


