package dk.aau.oose.graphics;

public class Foreground extends ImageElement {

	public Foreground() {
		super("assets/foreground_create.png");
	}
	
	public void setIsPlaying(boolean isPlaying){
		if(isPlaying){
			setImage("assets/foreground_play.png");
		} else {
			setImage("assets/foreground_create.png");
		}
	}

}
