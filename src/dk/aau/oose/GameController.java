package dk.aau.oose;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;
import dk.aau.oose.play.SavePerfect;

public class GameController extends GameElement {
	
	private CreateController createController;
	private PlayController playController;
	private boolean cooperative = false;
	
	private Button modeSelect,
				   onePlayerSelect, twoPlayerSelect,
				   savePerfectVersion;
	
	public GameController(){
		startCreate();
		initiateButtons();
	}

	private void initiateButtons() {
		modeSelect = new Button("Play!", 100, 40){
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
		
		onePlayerSelect = new Button("Singleplayer", 150, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						cooperative = false;
					}
				}
			}
		};
		
		twoPlayerSelect = new Button("Cooperative", 150, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						cooperative = true;
					}
				}
			}
		};
		
		savePerfectVersion = new Button("Bounce to disk", 100, 40){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						savePerfectVersion();
					}
				}
			}
		};
		
		modeSelect.setPosition(100, 10);
		onePlayerSelect.setPosition(300, 10);
		twoPlayerSelect.setPosition(500, 10);
		savePerfectVersion.setPosition(800, 10);

		this.addChild(modeSelect);
		this.addChild(onePlayerSelect);
		this.addChild(twoPlayerSelect);
		this.addChild(savePerfectVersion);
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
	
	private void savePerfectVersion(){
		NoteLineView nlvs[];
		if(playController != null){
			playController.stopPlaying();
			nlvs = playController.getTracks();
		} else {
			nlvs = createController.getTracks();
		}
		this.addChild(new SavePerfect(nlvs[0], nlvs[1], (cooperative ? 2 : 1), 180)); //TODO perhaps make a ui for setting the tempo?
	}
	
}
