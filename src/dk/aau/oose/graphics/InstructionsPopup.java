package dk.aau.oose.graphics;

import org.newdawn.slick.Input;

public class InstructionsPopup extends ImageElement {

	private int currentPage = 0;
	private static final int numberOfPages = 6;
	private Callback cb;
	
	public InstructionsPopup(InstructionsPopup.Callback cb) {
		super(getPagePath(0));
		this.cb = cb;
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
		} else if(key == Input.KEY_SPACE){
			goToNextPage();
		}
	}

	private void kill() {
		this.destroy();
		cb.call();
	}

	@Override
	public void mouseClicked(int btn, int x, int y, int clickCount) {
		//goToNextPage();
	}
	
	public static String getPagePath(int pageNumber){
		String path = String.format("assets/instructions/%02d.png", pageNumber);
		return path;
	}	
}
