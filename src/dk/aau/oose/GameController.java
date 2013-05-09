package dk.aau.oose;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.ButtonWithImage;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.graphics.Foreground;
import dk.aau.oose.graphics.InstructionsPopup;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;
import dk.aau.oose.play.PlayThread;
import dk.aau.oose.play.SavePerfect;

public class GameController extends GameElement {
	
	private CreateController createController;
	private PlayController playController;
	private boolean cooperative = false;
	private Foreground foreground;
	
	private final static int TEMPO_PLAY = 120,
							 TEMPO_BOUNCE = 180,
							 NUMBER_OF_BEATS = 50; //+2 beats for ending
	
	private ButtonWithImage modeButton,
							helpButton,
							cooperativeButton, 
							bounceButton;
	
	public GameController(){
		startCreate();
		initiateButtons();
		foreground = new Foreground();
		this.addChild(foreground);
		
		listen();
	}

	private void initiateButtons() {
		
		modeButton = new ButtonWithImage("assets/buttons/play.png"){
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
			
			@Override
			public void updateImage(){
				if(createController == null){
					setImage("assets/buttons/create.png");
				} else {
					setImage("assets/buttons/play.png");
				}
			}
		};
		
		
		
		cooperativeButton = new ButtonWithImage("assets/buttons/solo.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON && playController == null){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						setCooperative( !cooperative);
						updateImage();
					}
				}
			}
			
			@Override
			public void updateImage(){
				if(cooperative){
					if(createController == null){
						cooperativeButton.setImage("assets/buttons/coopDark.png");
					} else {
						cooperativeButton.setImage("assets/buttons/coop.png");	
					}
				} else {
					if(createController == null){
						cooperativeButton.setImage("assets/buttons/soloDark.png");
					} else {
						cooperativeButton.setImage("assets/buttons/solo.png");	
					}
				}
			}
			
		};

		
		bounceButton = new ButtonWithImage("assets/buttons/bounce.png"){
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON && bounceButton.getParent() != null){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						savePerfectVersion();
					}
				}
			}
		};
		
		helpButton = new ButtonWithImage("assets/buttons/help.png"){ //CHANGE THIS IMAGE TO A ? mark
			@Override
			public void mousePressed(int btn, int x, int y){
				if(btn == Input.MOUSE_LEFT_BUTTON && playController == null){
					if(this.hitTestPoint(this.globalToLocal(x, y))){
						showHelp();
					}
				}
			}
			
			@Override
			public void updateImage(){
				if(createController == null){
					helpButton.setImage("assets/buttons/helpDark.png"); //CHANGE THIS IMAGE TO A ? mark
				} else {
					helpButton.setImage("assets/buttons/help.png"); //CHANGE THIS IMAGE TO A ? mark
				}
			}
			
		};
		
		modeButton.setPosition(37, 35);
		helpButton.setPosition(85, 325);
		
		//Secret buttons
		cooperativeButton.setPosition(330, 350);//37, 335);
		bounceButton.setPosition(300, 50);

		this.addChild(modeButton);
		this.addChild(helpButton);

		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(c == '@'){
			if(bounceButton.getParent() == null){
				this.addChild(bounceButton);
				this.addChild(cooperativeButton);
			} else {
				this.removeChild(bounceButton);
				this.removeChild(cooperativeButton);
			}
		}
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
		
		updateButtonImages();
		foreground.setIsPlaying(true);
	}
	
	private void switchToCreate(NoteLineView [] nlvs){
		createController = new CreateController(nlvs[0], nlvs[1]);
		this.addChildAt(createController, 0);
		if(playController.isPlaying())
			playController.stopPlaying();
		this.removeChild(playController);
		playController = null;
		
		updateButtonImages();
		foreground.setIsPlaying(false);
	}
	
	private void startCreate(){
		createController = new CreateController(NUMBER_OF_BEATS, TEMPO_PLAY);
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
		this.addChild(new SavePerfect(nlvs[0], nlvs[1], (cooperative ? 2 : 1), TEMPO_BOUNCE)); //TODO perhaps make a ui for setting the tempo?
	}
	
	private void setCooperative(boolean cooperative){
		this.cooperative = cooperative;
	}
	
	private void showHelp(){
		System.out.println("show help");
		createController.unListenBranch();
		
		addChild(new InstructionsPopup(new InstructionsPopup.Callback() {
			@Override
			public void call() {
				createController.listenBranch();
			}
		}  ));
	}
	
	private void updateButtonImages() {
		modeButton.updateImage();
		cooperativeButton.updateImage();
		helpButton.updateImage();
	}
	
}
