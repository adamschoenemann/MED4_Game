package dk.aau.oose;

import org.newdawn.slick.Color;
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
	private boolean cooperative = false;
	
	private AButton modeSelect;
	private AButton onePlayerSelect, twoPlayerSelect;
	
	public GameController(){
		startCreate();
		initiateButtons();
	}

	private void initiateButtons() {
		modeSelect = new AButton("Play!", 100, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						if(playController != null){
							switchToCreate(playController.getTracks());
						} else {
							switchToPlay(createController.getTracks());
						}
					}
				}
			}
		};
		
		onePlayerSelect = new AButton("Singleplayer", 150, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						cooperative = false;
					}
				}
			}
		};
		
		twoPlayerSelect = new AButton("Cooperative", 150, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						cooperative = true;
					}
				}
			}
		};
		
		modeSelect.setPosition(100, 10);
		onePlayerSelect.setPosition(300, 10);
		twoPlayerSelect.setPosition(500, 10);

		this.addChild(modeSelect);
		this.addChild(onePlayerSelect);
		this.addChild(twoPlayerSelect);
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
		playController = new PlayController(nlvs[0], nlvs[1], (cooperative ? 2 : 1) );
		this.addChildAt(playController, 0);
		this.removeChild(createController);
		createController = null;
		modeSelect.setText("Create!");
	}
	
	private void switchToCreate(NoteLineView [] nlvs){
		createController = new CreateController(nlvs[0], nlvs[1]);
		this.addChildAt(createController, 0);
		if(playController.isPlaying())
			playController.stopPlaying();
		this.removeChild(playController);
		playController = null;
		modeSelect.setText("Play!");
	}
	
	private void startCreate(){
		createController = new CreateController();
		this.addChild(createController);
	}
	
}
