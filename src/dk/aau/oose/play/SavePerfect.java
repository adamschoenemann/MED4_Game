package dk.aau.oose.play;

import java.util.Calendar;

import dk.aau.oose.core.GameElement;
import dk.aau.oose.noteline.NoteLinePlayer;
import dk.aau.oose.noteline.NoteLineView;


public class SavePerfect extends GameElement {
	
	private int numberOfComposers;
	private PlayTrack pt1, pt2;

	public SavePerfect(NoteLineView nlv1, NoteLineView nlv2, int numberOfComposers, int tempo) {		
		pt1 = new PlayTrack(setTempo(nlv1, tempo), 0, false);
		pt2 = new PlayTrack(setTempo(nlv2, tempo), 0, false);
		
		this.numberOfComposers = numberOfComposers;

		pt1.startPlaying();
		pt2.startPlaying(new PlayThread.Callback() {
			@Override
			public void call() {
				saveAndKill();
			}
		});

	}
	
	private static NoteLineView setTempo(NoteLineView nlv, int tempo){
		NoteLinePlayer in =  nlv.getNoteLinePlayer();
		
		NoteLinePlayer nlp = new NoteLinePlayer(in.getNoteLine(), in.getStartOctave(), in.getNotesPerOctave(), tempo);
		
		return new NoteLineView(nlp, nlv.getWidth(), nlv.getHeight());
	}
	
	private void saveAndKill(){
		saveLastPlaythrough(numberOfComposers);
		this.destroy();
	}
	
	private void saveLastPlaythrough(int numberOfComposers){
		System.out.println("Saving.");
		pt1.getNoteLineView().getNoteLinePlayer().saveLastTakeAs( generateFilename(numberOfComposers > 1));
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
