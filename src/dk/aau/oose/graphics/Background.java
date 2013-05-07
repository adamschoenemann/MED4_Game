package dk.aau.oose.graphics;

public class Background extends ImageElement {

	private float moveSpeed;
	private boolean move = true;
	
	public Background(float moveSpeed){
		super("assets/bg.png");
		this.moveSpeed = moveSpeed;
	}
	
	@Override
	public void onUpdate() {
		if(move) setPosition(getPosition().x - moveSpeed, getPosition().y);
	}
	
	public void stop(){
		move = false;
	}
	
}
