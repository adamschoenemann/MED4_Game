package dk.aau.oose.create;

import org.newdawn.slick.Input;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;

public class CreateController extends GameElement {
	private CreateTrack ct1, ct2;
	private static final int SCROLL_RIGHT = Input.KEY_D, 
							 SCROLL_LEFT = Input.KEY_A, 
							 SCROLL_SPEED = 10,
							 MAX_NOTE = 10,
							 NUMBER_OF_BEATS = 32,
							 TRACK1_OCTAVE = 5,
							 TRACK2_OCTAVE = 3,
							 TEMPO = 100,
							 WIDTH = NUMBER_OF_BEATS * 50,
							 HEIGHT = 300,
							 PT_VERTICAL_OFFSET = 90;
	
	
	public CreateController(NoteLineView nlv1, NoteLineView nlv2){
		ct1 = new CreateTrack(nlv1);
		ct2 = new CreateTrack(nlv2);
		
		ct1.setPosition(0.0f, PT_VERTICAL_OFFSET);
		ct2.setPosition(0.0f, PT_VERTICAL_OFFSET + ct1.getPosition().y + ct1.getBounds().height);
		
		this.addChild(ct1);
		this.addChild(ct2);
	}
	
	public CreateController(){
		this(NoteLineView.newTestInstance(MAX_NOTE, NUMBER_OF_BEATS, TRACK1_OCTAVE, 5, TEMPO, WIDTH, HEIGHT), 
			 NoteLineView.newTestInstance(MAX_NOTE, NUMBER_OF_BEATS, TRACK2_OCTAVE, 5, TEMPO, WIDTH, HEIGHT));
	}
	
	@Override
	public void onUpdate() {
		Input input = getGameContainer().getInput();
		if(input.isKeyDown(SCROLL_LEFT)){ // TODO find alternative input to scroll
			scroll(SCROLL_SPEED);
		} else if(input.isKeyDown(SCROLL_RIGHT)){
			scroll(-SCROLL_SPEED);
		}
		
	}
	
	public void scroll(float pixels){
		System.out.println("Scroll " + pixels);
		ct1.moveTrack(pixels);
		ct2.moveTrack(pixels);
	}
	
	public NoteLineView[] getTracks(){
		NoteLineView[] nlvs = new NoteLineView[2];
		nlvs[0] = ct1.getNoteLineView();
		nlvs[1] = ct2.getNoteLineView();
		
		return nlvs;
	}
	
}
