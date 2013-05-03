package dk.aau.oose.play;

import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;

public class PlayController extends GameElement {
	private PlayTrack pt1, pt2;
	private static final int PT1_CONTROLLER = Input.KEY_A, 
							 PT2_CONTROLLER = Input.KEY_L,
							 PT_VERTICAL_OFFSET = 50;
	
	
	public PlayController(NoteLineView nlv1, NoteLineView nlv2, boolean cooperative){
		pt1 = new PlayTrack(nlv1, PT1_CONTROLLER, false);
		pt2 = new PlayTrack(nlv2, PT2_CONTROLLER, true);
		
		pt1.setPosition(0.0f, PT_VERTICAL_OFFSET);
		pt2.setPosition(0.0f, PT_VERTICAL_OFFSET + pt1.getPosition().y + pt1.getBounds().y);
		
		this.addChild(pt1);
		this.addChild(pt2);
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
	
	
	
}
