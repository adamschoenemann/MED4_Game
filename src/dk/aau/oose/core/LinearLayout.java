package dk.aau.oose.core;

public class LinearLayout extends GameElement {
	
	private int padding = 5;
	public static final int HORIZONTAL = 1, VERTICAL = 2;
	private int direction = HORIZONTAL;
	
	public LinearLayout(){
		this(HORIZONTAL);
	}
	
	public LinearLayout(int dir){
		setDirection(dir);
	}
	
	@Override
	public void addChild(GameElement child) {
		super.addChild(child);
		updatePositions();
	}
	
	public void updatePositions(){
		getChildAt(0).setPosition(0, 0);
		for(int i = 1; i < numChildren(); i++){
			GameElement prev = getChildAt(i - 1);
			GameElement now = getChildAt(i);
			if(direction == HORIZONTAL) {
				now.setPosition(prev.getX() + prev.getWidth() + padding, 0);
			}
			else if(direction == VERTICAL){
				now.setPosition(0, prev.getY() + prev.getHeight() + padding);
			}
		}
		computeBounds();
	}

	@Override
	public boolean removeChild(GameElement child) {
		boolean ret = super.removeChild(child);
		updatePositions();
		return ret;
	}
	
	public void setPadding(int pad){
		padding = pad;
	}
	
	public int getPadding(){
		return padding;
	}
	
	public void setDirection(int dir){
		if(dir != HORIZONTAL && dir != VERTICAL){
			throw new IllegalArgumentException("Invalid direction specified");
		}
		else {
			direction = dir;
		}
		
	}
	
	public int getDirection(){
		return direction;
	}
	
}
