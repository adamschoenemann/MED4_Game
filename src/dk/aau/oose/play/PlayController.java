package dk.aau.oose.play;

import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.graphics.Background;
import dk.aau.oose.graphics.ImageElement;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.scores.HighScoreInput;
import dk.aau.oose.play.scores.HighScoreScreen;

public class PlayController extends GameElement {
	protected PlayTrack pt1, pt2;

	private Background bg;
	private HighScoreScreen hss;
	
	private static final int SINGLEPLAYER_CONTROLLER = Input.KEY_SPACE,
							 PT1_CONTROLLER = Input.KEY_A, 
							 PT2_CONTROLLER = Input.KEY_L;
	
	
	public PlayController(NoteLineView nlv1, NoteLineView nlv2, int numberOfPlayers){
		pt1 = new PlayTrack(nlv1, (numberOfPlayers > 1) ? PT1_CONTROLLER : SINGLEPLAYER_CONTROLLER, (numberOfPlayers > 0));
		pt2 = new PlayTrack(nlv2, (numberOfPlayers > 1) ? PT2_CONTROLLER : 0, (numberOfPlayers > 1));
		
		
		bg = new Background(0.5f);
		
		pt1.setPosition(0.0f, CreateController.VERTICAL_OFFSET_1);
		pt2.setPosition(0.0f, CreateController.VERTICAL_OFFSET_2 + pt1.getPosition().y + pt1.getBounds().height);
		
		this.addChild(bg);	
		this.addChild(pt1);
		this.addChild(pt2);

		startPlaying(); //Start right away; no need to press space.

	}
	
	public void startPlaying(){
		
		hss = new HighScoreScreen(){
			@Override
			public void onRemoved(){
				toggleHighScoreScreen(false);
			}
		};
		
		pt1.startPlaying();
		pt2.startPlaying();
		
		(new Thread(new Runnable(){

			@Override
			public void run() {
				
				
				try {
					pt1.getThread().join();
					pt2.getThread().join();
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				addHighScoreInput(pt1);
				addHighScoreInput(pt2);
				toggleHighScoreScreen(true);
				System.out.println("Both done!");
			}
			
		})).start();
		
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

	private synchronized void addHighScoreInput(PlayTrack pt) {
		HighScoreInput hsi = new HighScoreInput(pt.getScore().getScore());
//		hsi.setPosition((getGameContainer().getWidth() - hsi.getWidth()) / 2,
//				(getGameContainer().getHeight() - hsi.getHeight()) / 2);
		hss.addHighScoreInput(hsi);
	}
	
	private synchronized void toggleHighScoreScreen(boolean toggle){
		if(toggle){
			this.removeChild(pt1);
			this.removeChild(pt2);
			this.removeChild(bg);
			this.addChild(hss);
		} else {
			this.addChild(bg);
			this.addChild(pt1);
			this.addChild(pt2);

		}
		
	}
	
	@Override
	public void onUpdate() {
		if(! isPlaying()){
			bg.stop();
		}
	}
}
