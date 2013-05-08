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
	
	public void flipNoteDistinct(int pos){
		Note note = getNote(pos);
		note.setDistinct(!note.isDistinct());
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
		if(pos >= getNumBeats())
			pos = getNumBeats() - 1;
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
	
	public static NoteLine newEmptyInstance(int steps, int numBeats){
		return newFixedValueInstance(0, steps, numBeats);
	}
	
	public static NoteLine newFixedValueInstance(int value, int steps, int numBeats){
		NoteLine nl = new NoteLine(steps, numBeats);
		
		for(int i = 0; i < numBeats; i++){
			nl.setNoteValue(value, i);
		}
		
		return nl;
	}
	
	public void fixDistinct(int index){
		if(index >= getNumBeats() || index < 0)
			return; //Bail out if outside array.
		
		if(index == getNumBeats() - 1){			//If last note, set to distinct
			beats[index].setDistinct(true);
			return;
		}
		if(beats[index].isDistinct() == false){				//if not distinct, but unequal to following note, set to distinct
			int val = beats[index].getValue();
			int nextVal = beats[index + 1].getValue();
			if(val != nextVal){								
				beats[index].setDistinct(true);
			}	
		}
		if(index > 0 && beats[index - 1].isDistinct() == false){ //If not first note, and previous note is not distinct, and if value unequal to previous note, set previous to distinct.
			int val = beats[index].getValue();
			int prevVal = beats[index - 1].getValue();
			if(val != prevVal){								
				beats[index - 1].setDistinct(true);
				fixDistinct(index - 1);
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
