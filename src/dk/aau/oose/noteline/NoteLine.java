package dk.aau.oose.noteline;

public class NoteLine {
	
	
	private final int MAX_NOTE;
	private final Note[] beats;
	
	/**
	 * @param maxNoteValue The maximum note value. Usually 10 for a range of two octaves.
	 * @param numBeats The total number of beats in the NoteLine. Usually 128 for 8 * 16 beats.
	 */
	public NoteLine(int maxNoteValue, int numBeats){
		this.MAX_NOTE = maxNoteValue;
		this.beats = new Note[numBeats];
		for(int i = 0; i < numBeats; i++){
			beats[i] = new Note(0);
		}
		for(int i = 0; i < numBeats; i++){
			fixDistinct(i);
		}
	}
	
	public void setNoteValue(int val, int pos){
		Note note = getNote(pos);
		note.setValue(val);
		setNote(note, pos);
	}
	
	public void setNote(Note note, int pos){
		int val = note.getValue();
		if(val <= MAX_NOTE && val >= 0) {
			beats[pos].setValue(note.getValue());
			beats[pos].setDistinct(note.isDistinct());
		} else {
			throw new IllegalArgumentException("Invalid note value");
		}
		fixDistinct(pos);
	}
	
	public Note getNote(int pos){
		return beats[pos].clone();
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
	
	public void fixDistinct(int index){
		if(index == getNumBeats() - 1){
			beats[index].setDistinct(true);
			return;
		}
		if(beats[index].isDistinct() == false){
			int val = beats[index].getValue();
			int nextVal = beats[index + 1].getValue();
			if(val != nextVal){
				beats[index].setDistinct(true);
			}	
		}
		if(index > 0 && beats[index - 1].isDistinct() == false){
			int val = beats[index].getValue();
			int prevVal = beats[index - 1].getValue();
			if(val != prevVal){
				beats[index - 1].setDistinct(true);
			}
		}
		
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
