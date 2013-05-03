package dk.aau.oose;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.AButton;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;

public class GameController extends GameElement {
	
	private CreateController createController;
	private PlayController playController;
	private boolean cooperative;
	
	public GameController(boolean cooperative){
		this.cooperative = cooperative;
		startCreate();
		
		GameElement switchButton = new AButton("Switch Modes!", 200, 40){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(x, y)){
						if(playController != null){
							switchToCreate(playController.getTracks());
						} else {
							switchToPlay(createController.getTracks());
						}
					}
				}
			}
			
		};

		this.addChild(switchButton);
		switchButton.setPosition(100, 0);
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDraw(Graphics gfx) {
		// TODO Auto-generated method stub
	}
	
	private void switchToPlay(NoteLineView [] nlvs){
		playController = new PlayController(nlvs[0], nlvs[1], cooperative);
		this.addChild(playController);
		createController.destroy();
		createController = null;
	}
	
	private void switchToCreate(NoteLineView [] nlvs){
		createController = new CreateController(nlvs[0], nlvs[1]);
		this.addChild(createController);
		if(playController.isPlaying())
			playController.stopPlaying();
		playController.destroy();
		playController = null;
	}
	
	private void startCreate(){
		createController = new CreateController();
		this.addChild(createController);
	}
	
}
