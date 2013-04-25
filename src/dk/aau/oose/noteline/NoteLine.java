package dk.aau.oose.noteline;

public class NoteLine {
	
	
	private final int MAX_NOTE;
	private final Note[] beats;
	
	public NoteLine(int steps, int numBeats){
		this.MAX_NOTE = steps;
		this.beats = new Note[numBeats];
		for(int i = 0; i < numBeats; i++){
			beats[i] = new Note(0);
		}
	}
	
	public void setNoteValue(int val, int pos){
		Note note = getNote(pos);
		note.setValue(val);
		// Only for error checking
		// Doesn't actually change any state (setValue already did that)
		setNote(note, pos);
	}
	
	public void setNote(Note note, int pos){
		int val = note.getValue();
		if(val <= MAX_NOTE && val >= 0) {
			beats[pos] = note;
		} else {
			throw new IllegalArgumentException("Invalid note value");
		}
	}
	
	public Note getNote(int pos){
		return beats[pos];
	}
	
	public int getNumBeats(){
		return beats.length;
	}
	
	public int getMaxNote(){
		return MAX_NOTE;
	}
	
	public static NoteLine newTestInstance(int steps, int numBeats){
		NoteLine nl = new NoteLine(steps, numBeats);
		
		for(int i = 0; i < numBeats; i++){
			int val = (int) Math.round(Math.random() * nl.MAX_NOTE);
			nl.setNoteValue(val, i);
		}
		
		return nl;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("|");
		for(int i = 0; i < beats.length; i++){
			sb.append(beats[i]).append("|");
		}
		
		return sb.toString();
	}
	
}
