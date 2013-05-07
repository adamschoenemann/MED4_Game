package dk.aau.oose.play;

import java.util.Calendar;

import org.newdawn.slick.Input;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.graphics.Background;
import dk.aau.oose.noteline.NoteLineView;

public class PlayController extends GameElement {
	protected PlayTrack pt1, pt2;
	private Background bg;
	private Button saveButton;
	private int numberOfPlayers;
	
	private static final int SINGLEPLAYER_CONTROLLER = Input.KEY_SPACE,
							 PT1_CONTROLLER = Input.KEY_A, 
							 PT2_CONTROLLER = Input.KEY_L,
							 PT_VERTICAL_OFFSET = 50;
	
	
	public PlayController(NoteLineView nlv1, NoteLineView nlv2, int numberOfPlayers){
		pt1 = new PlayTrack(nlv1, (numberOfPlayers > 1) ? SINGLEPLAYER_CONTROLLER : PT1_CONTROLLER, (numberOfPlayers > 0));
		pt2 = new PlayTrack(nlv2, PT2_CONTROLLER, (numberOfPlayers > 1));
		bg = new Background();
		this.numberOfPlayers = numberOfPlayers;
		
		pt1.setPosition(0.0f, PT_VERTICAL_OFFSET);
		pt2.setPosition(0.0f, PT_VERTICAL_OFFSET + pt1.getPosition().y + pt1.getBounds().height);
		
		this.addChild(bg);	
		this.addChild(pt1);
		this.addChild(pt2);
		
		initiateButton();
		listen();
		startPlaying(); //Start right away; no need to press space.
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
		saveButton = new Button("Save last playthrough", 100, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						saveLastPlaythrough();
					}
				}
			}
		};
		
		saveButton.setPosition(800, 10);
		
		this.addChild(saveButton);
	}
	
	private void saveLastPlaythrough(){		
		pt1.getNoteLineView().getNoteLinePlayer().saveLastTakeAs( generateFilename(numberOfPlayers > 1));
	}
	
	
	private static String generateFilename(boolean cooperative){
		Calendar rightNow = Calendar.getInstance();
		int dayOfMonth = rightNow.get(Calendar.DAY_OF_MONTH);
		int hour = rightNow.get(Calendar.HOUR_OF_DAY);
		int minutes = rightNow.get(Calendar.MINUTE);
		int seconds = rightNow.get(Calendar.SECOND);
		
		String dateString = new String(Integer.toString(dayOfMonth) + "-" + Integer.toString(hour) + "-" + Integer.toString(minutes) + "-" + Integer.toString(seconds)); 
				
		if(cooperative){
			return new String("Coop-" + dateString);
		} else {
			return new String("Single-" + dateString);
		}
	}
	
}
