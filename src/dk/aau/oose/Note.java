package dk.aau.oose;

//LOL comment LOL LOL LOL

@Deprecated
public class Note {
	
	private int position, octave, duration;
	
	public Note(int pos, int oct, int dur){
		setPosition(pos);
		setOctave(oct);
		setDuration(dur);
	}
	
	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		if(position < 0 || position > 5){
			throw new IllegalArgumentException("Illegal position value");
		}
		this.position = position;
	}

	public int getOctave() {
		return octave;
	}

	public void setOctave(int octave) {
		if(octave < 0 || octave > 3){
			throw new IllegalArgumentException("Illegal octave value");
		}
		this.octave = octave;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
