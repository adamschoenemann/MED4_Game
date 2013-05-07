package dk.aau.oose;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import dk.aau.oose.core.Button;
import dk.aau.oose.core.ButtonWithImage;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.graphics.Foreground;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;
import dk.aau.oose.play.SavePerfect;

public class GameController extends GameElement {
	
	private CreateController createController;
	private PlayController playController;
	private boolean cooperative = false;
	private Foreground foreground;
	
	private ButtonWithImage modeSelect,
							singleSelect, 
							coopSelect,
							savePerfectVersion;
	
	public GameController(){
		startCreate();
		initiateButtons();
		foreground = new Foreground();
		this.addChild(foreground);
	}

	private void initiateButtons() {
		modeSelect = new ButtonWithImage("assets/buttons/play.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						if(playController == null){
							switchToPlay(createController.getTracks());
						} else {
							switchToCreate(playController.getTracks());
						}
					}
				}
			}
		};
		
		singleSelect = new ButtonWithImage("assets/buttons/single.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						setCooperative(false);
					}
				}
			}
		};
		
		coopSelect = new ButtonWithImage("assets/buttons/coopDark.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						setCooperative(true);
					}
				}
			}
		};
		
		savePerfectVersion = new ButtonWithImage("assets/buttons/bounce.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						savePerfectVersion();
					}
				}
			}
		};
		
		modeSelect.setPosition(38, 50);
		singleSelect.setPosition(620, 50);
		coopSelect.setPosition(640, 350);
		savePerfectVersion.setPosition(800, 10);

		this.addChild(modeSelect);
		this.addChild(singleSelect);
		this.addChild(coopSelect);
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
		modeSelect.setImage("assets/buttons/create.png");
	}
	
	private void switchToCreate(NoteLineView [] nlvs){
		createController = new CreateController(nlvs[0], nlvs[1]);
		this.addChildAt(createController, 0);
		if(playController.isPlaying())
			playController.stopPlaying();
		this.removeChild(playController);
		playController = null;
		modeSelect.setImage("assets/buttons/play.png");
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
	
	private void setCooperative(boolean cooperative){
		this.cooperative = cooperative;
		if(cooperative){
			singleSelect.setImage("assets/buttons/singleDark.png");
			coopSelect.setImage("assets/buttons/coop.png");
		} else {
			coopSelect.setImage("assets/buttons/coopDark.png");
			singleSelect.setImage("assets/buttons/single.png");
		}
	}
	
}
