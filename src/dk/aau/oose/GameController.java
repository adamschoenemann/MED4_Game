package dk.aau.oose;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import dk.aau.oose.core.AButton;
import dk.aau.oose.core.GameElement;
import dk.aau.oose.core.GameWorld;
import dk.aau.oose.create.CreateController;
import dk.aau.oose.noteline.Note;
import dk.aau.oose.noteline.NoteLine;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;
import dk.aau.oose.play.PlayController;

public class GameController extends GameElement {
	
	private CreateController createCtrl;
	private PlayController playCtrl;
	
	public GameController(int maxNote, int numBeats){
		GameContainer gc = GameWorld.getGameContainer();
		NoteLine nl = new NoteLine(maxNote, numBeats);
		int[][] comp = {
				{1, 1}, {2, 1}, {3, 1}, {4, 1},
				{5, 0}, {5, 0}, {5, 1}, {5, 0},
				{9, 1}, {9, 0}, {1, 1}, {2, 0},
				{2, 0}, {5, 1}, {4, 1}, {1, 0}
		};
		assert(comp.length == numBeats);

		for(int i = 0; i < nl.getNumBeats(); i++){
			Note note = nl.getNote(i);
			note.setValue(comp[i][0]);
			note.setDistinct((comp[i][1] == 1) ? true : false);
			nl.setNote(note, i);
		}
		NoteLinePlayer nlp = new NoteLinePlayer(nl, 1, 5, 180);
		NoteLineView nlv = new NoteLineView(nlp, gc.getWidth(), gc.getHeight());
		nlv.setColor(Color.blue);

		System.out.println(nl);
		createCtrl = new CreateController(nlv);

		playCtrl = new PlayController(nlv, Input.KEY_J); // TODO change default key from 'j' to something else, allowing for two players.

		this.addChild(createCtrl);
		//this.addChild(playCtrl);
		GameElement switchButton = new AButton("Switch Modes!", 200, 40){
			
			@Override
			public void mousePressed(int btn, int x, int y){
				//System.out.format("Mouse: (%d, %d)\n", x, y);
				if(btn == Input.MOUSE_LEFT_BUTTON){
//					Vector2f localPos = this.globalToLocal(x, y);
//					System.out.format("Local: (%f, %f)\n", localPos.x, localPos.y);
					if(this.hitTestPoint(x, y)){
						System.out.println("clicked!");
					}
				}
			}
			
		};
		//GameWorld.getGameContainer().getInput().addListener(switchButton);
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
	
}
