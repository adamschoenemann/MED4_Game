package dk.aau.oose.play;

import java.util.Date;

import org.newdawn.slick.Input;

import dk.aau.oose.core.AButton;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.graphics.Background;
import dk.aau.oose.noteline.NoteLineView;

public class PlayController extends GameElement {
	private PlayTrack pt1, pt2;
	private Background bg;
	private AButton saveButton;
	private boolean cooperative;
	
	private static final int PT1_CONTROLLER = Input.KEY_A, 
							 PT2_CONTROLLER = Input.KEY_L,
							 PT_VERTICAL_OFFSET = 50;
	
	
	public PlayController(NoteLineView nlv1, NoteLineView nlv2, boolean cooperative){
		pt1 = new PlayTrack(nlv1, PT1_CONTROLLER, true);
		pt2 = new PlayTrack(nlv2, PT2_CONTROLLER, cooperative);
		bg = new Background();
		this.cooperative = cooperative;
		
		pt1.setPosition(0.0f, PT_VERTICAL_OFFSET);
		pt2.setPosition(0.0f, PT_VERTICAL_OFFSET + pt1.getPosition().y + pt1.getBounds().height);
		
		this.addChild(bg);	
		this.addChild(pt1);
		this.addChild(pt2);
		
		initiateButton();
		
		listen();
	}
	
	public void startPlaying(){
		pt1.startPlaying();
		pt2.startPlaying();
	}
	
	public boolean isPlaying(){
		if(pt1.isPlaying() || pt2.isPlaying()){
			return true;
		} else {
			return false;
		}
	}
	
	public void stopPlaying(){
		pt1.stopPlaying();
		pt2.stopPlaying();
	}
	
	public NoteLineView[] getTracks(){
		NoteLineView[] nlvs = new NoteLineView[2];
		nlvs[0] = pt1.getNoteLineView();
		nlvs[1] = pt2.getNoteLineView();
		
		return nlvs;
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_SPACE && !isPlaying()){
			startPlaying();
		}
	}
	
	private void initiateButton(){
		saveButton = new AButton("Save", 700, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						saveLastPlaythrough();
					}
				}
			}
		};
		
		this.addChild(saveButton);
	}
	
	private void saveLastPlaythrough(){
		Date date = new Date();
		if(cooperative){
			saveAs("Coop " + date.toString());
		} else {
			saveAs("Single " + date.toString());
		}
		
		System.out.println("Save last playthrough...");
	}
	
	private void saveAs(String name){
		System.out.println("Save as " + name);
		pt1.getNoteLineView().getNoteLinePlayer().saveLastTakeAs(name);
	}
	
}
