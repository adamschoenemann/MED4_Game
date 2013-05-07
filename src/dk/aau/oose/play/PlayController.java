package dk.aau.oose.play;

import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.graphics.Background;
import dk.aau.oose.noteline.NoteLineView;

public class PlayController extends GameElement {
	protected PlayTrack pt1, pt2;
	private Background bg;
	
	private static final int SINGLEPLAYER_CONTROLLER = Input.KEY_SPACE,
							 PT1_CONTROLLER = Input.KEY_A, 
							 PT2_CONTROLLER = Input.KEY_L,
							 PT_VERTICAL_OFFSET = 50;
	
	
	public PlayController(NoteLineView nlv1, NoteLineView nlv2, int numberOfPlayers){
		pt1 = new PlayTrack(nlv1, (numberOfPlayers > 1) ? SINGLEPLAYER_CONTROLLER : PT1_CONTROLLER, (numberOfPlayers > 0));
		pt2 = new PlayTrack(nlv2, PT2_CONTROLLER, (numberOfPlayers > 1));
		bg = new Background();
		
		pt1.setPosition(0.0f, PT_VERTICAL_OFFSET);
		pt2.setPosition(0.0f, PT_VERTICAL_OFFSET + pt1.getPosition().y + pt1.getBounds().height);
		
		this.addChild(bg);	
		this.addChild(pt1);
		this.addChild(pt2);
		
		//listen();
		//Start right away; no need to press space.
		startPlaying(new PlayThread.Callback() {
			
			@Override
			public void call() {
				// TODO Auto-generated method stub
				
			}
		});
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
