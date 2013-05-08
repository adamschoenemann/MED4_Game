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
		
		//Generate filename like: "Coop_2013-5-8_10-11-56.aiff"
		
		Calendar rightNow = Calendar.getInstance();
		
		String dateString = new String(
						Integer.toString(rightNow.get(Calendar.YEAR))
				+ "-" + Integer.toString(rightNow.get(Calendar.MONTH) + 1) 
				+ "-" + Integer.toString(rightNow.get(Calendar.DATE)) 
				+ "_"+ Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY))
				+ "-" + Integer.toString(rightNow.get(Calendar.MINUTE))
				+ "-" + Integer.toString(rightNow.get(Calendar.SECOND))
				 + ".aiff"); 
				
				
		if(cooperative){
			return new String("Coop_" + dateString);
		} else {
			return new String("Solo_" + dateString);
		}
	}
}
