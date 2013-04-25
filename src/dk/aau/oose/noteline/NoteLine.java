package dk.aau.oose.noteline;

public class NoteLine {
	
	
	private final int MAX_NOTE;
	private final int[] beats;
	
	public NoteLine(int steps, int numBeats){
		this.MAX_NOTE = steps;
		this.beats = new int[numBeats];
	}
	
	public void setNote(int val, int pos){
		if(val <= MAX_NOTE && val >= 0) {
			beats[pos] = val;
		} else {
			throw new IllegalArgumentException("Invalid note value");
		}
	}
	
	public int getNote(int pos){
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
			nl.setNote(val, i);
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
