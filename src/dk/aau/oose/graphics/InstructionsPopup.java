package dk.aau.oose.graphics;

import org.newdawn.slick.Input;

public class InstructionsPopup extends ImageElement {

	private int currentPage = 0;
	private static final int numberOfPages = 6;
	private Callback cb;
	private boolean cooperative;
	
	public InstructionsPopup(InstructionsPopup.Callback cb, boolean cooperative) {
		super("assets/instructions/00.png");
		this.cb = cb;
		this.cooperative = cooperative;
	}
	
	public static interface Callback{
		public void call();
	}
	
	private void goToNextPage(){
		if(currentPage + 1 < numberOfPages){
			this.setImage(getPagePath(++currentPage));
			
		} else {
			kill();
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		if(key == Input.KEY_ESCAPE){
			kill();
		} else {//if(key == Input.KEY_SPACE){
			goToNextPage();
		}
	}

	private void kill() {
		this.destroy();
		cb.call();
	}

	@Override
	public void mousePressed(int btn, int x, int y) {
		goToNextPage();
	}
	
	private String getPagePath(int pageNumber){
		String path;
		if(pageNumber == numberOfPages - 1){
			path = String.format("assets/instructions/%02d", pageNumber) + ((cooperative) ? "c" : "s") + ".png";
		} else {
			path = String.format("assets/instructions/%02d.png", pageNumber);
		}
		
		return path;
	}	
}
