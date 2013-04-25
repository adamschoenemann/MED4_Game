package dk.aau.oose.noteline;

public class Note {
	
	private int val;
	private boolean distinct;
	
	public Note(int val){
		this(val, true);
	}
	
	public Note(int val, boolean distinct){
		this.val = val;
		this.distinct = distinct;
	}

	public int getValue(){
		return val;
	}
	
	public void setValue(int val){
		this.val = val;
	}
	
	public void setDistinct(boolean distinct){
		this.distinct = distinct;
	}
	
	public boolean isDistinct(){
		return this.distinct;
	}
}
