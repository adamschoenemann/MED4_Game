package dk.aau.oose.create;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLineView;

public class CreateController extends GameElement {
	private CreateTrack ct1, ct2;
	public static final int SCROLL_BUTTON = 1,
							 MAX_NOTE = 10,
							 NUMBER_OF_BEATS = 32,
							 TRACK1_OCTAVE = 3,
							 TRACK2_OCTAVE = 1,
							 TEMPO = 100,
							 WIDTH = NUMBER_OF_BEATS * 30,
							 HEIGHT = 150,
							 VERTICAL_OFFSET_1 = 130,
							 VERTICAL_OFFSET_2 = 150;
	
	private boolean scrolling;
	private float scrollStartX;
	
	
	public CreateController(NoteLineView nlv1, NoteLineView nlv2){
		ct1 = new CreateTrack(nlv1);
		ct2 = new CreateTrack(nlv2);
		
		ct1.setPosition(0.0f, VERTICAL_OFFSET_1);
		ct2.setPosition(0.0f, VERTICAL_OFFSET_2 + ct1.getPosition().y + ct1.getBounds().height);
		
		this.addChild(ct1);
		this.addChild(ct2);
		
		listen();
	}
	
	public CreateController(){
		this(NoteLineView.newEmptyInstance(MAX_NOTE, NUMBER_OF_BEATS, TRACK1_OCTAVE, 5, TEMPO, WIDTH, HEIGHT), 
			 NoteLineView.newEmptyInstance(MAX_NOTE, NUMBER_OF_BEATS, TRACK2_OCTAVE, 5, TEMPO, WIDTH, HEIGHT));
	}
	
	@Override
	public void onUpdate() {
		
		if(scrolling){
			float speed = (scrollStartX - getGameContainer().getInput().getMouseX()) * 0.075f;
			scroll(speed);
		}

	}
	
	@Override
	public void keyPressed(int key, char c) {
		
	}
	
	@Override
	public void mousePressed(int btn, int x, int y) {
		if(btn == SCROLL_BUTTON){
			scrolling = true;
			scrollStartX = x;
		}
	}
	
	public void mouseReleased(int btn, int x, int y) {
		if(btn == SCROLL_BUTTON){
			scrolling = false;
		}
	}
	
	public void scroll(float pixels){
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
