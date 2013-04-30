package dk.aau.oose.core;



import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.MouseListener;

import dk.aau.oose.container.Container;
import dk.aau.oose.display.IDrawable;
import dk.aau.oose.display.ITransform;
import dk.aau.oose.display.Transform;

/**
 * Base class for all elements in the game
 * Extends Container and implements IDrawable and ITransform
 * Other GameElements can be added as children
 * Override {@link #onUpdate()} and {@link #onDraw(Graphics)} to provide functionality
 * and graphics 
 * @author Adam
 *
 */
public class GameElement extends Container<GameElement> implements IDrawable, ITransform, InputListener {

	
	private final Transform transform = new Transform();
	private final Vector2f dimensions = new Vector2f();
	
	public GameElement(){
		Input input = GameWorld.getGameContainer().getInput();
		input.addListener(this);
	}
	
	/**
	 * Called in the update loop
	 */
	public void onUpdate(){
		
	}
	/**
	 * Called in the draw loop. Override to to create visuals
	 * REMEMBER! call setDimensions() with your graphical dimensions
	 * in order for the rest of the functionality to work properly
	 * @param gfx - a Graphics to use for drawing 
	 */
	public void onDraw(Graphics gfx){
		
	}
	
	/**
	 * Update the element and all its children
	 */
	public final void update(){
		this.onUpdate();		
		for(int i = 0; i < numChildren(); i++){
			getChildAt(i).update();
		}
	}
	
	/**
	 * Draw the element and all its children
	 */
	public final void draw(){
		Graphics gfx = GameWorld.getGameContainer().getGraphics();
		gfx.pushTransform();
		gfx.translate(getPosition().x, getPosition().y);
		gfx.rotate(0, 0, getRotation());
		onDraw(gfx);
		for(int i = 0; i < numChildren(); i++){
			getChildAt(i).draw();
		}
		gfx.popTransform();
	}

	
	@Override
	public Vector2f getPosition() {
		return transform.getPosition();
	}

	@Override
	public void setPosition(Vector2f vec) {
		transform.setPosition(vec);
	}

	@Override
	public void setPosition(float x, float y) {
		transform.setPosition(x, y);
	}

	@Override
	public float getRotation() {
		return transform.getRotation();
	}

	@Override
	public void setRotation(float rot) {
		transform.setRotation(rot);
	}

	@Override
	public Vector2f getScale() {
		return transform.getScale();
	}

	@Override
	public void setScale(Vector2f vec) {
		transform.setScale(vec);
	}

	@Override
	public void setScale(float w, float h) {
		transform.setScale(w, h);
	}

	public Vector2f getDimensions(){
		return dimensions;
	}
	
	public void setDimensions(Vector2f vec){
		setDimensions(vec.x, vec.y);
	}
	
	public void setDimensions(float x, float y){
		dimensions.x = x;
		dimensions.y = y;
	}
	
	/**
	 * Converts an input global coordinate into local coordinate space
	 * @param input - a global coordinate vector
	 * @return a vector containing the local coordinates of input
	 */
	public Vector2f globalToLocal(Vector2f input){
		// TODO: Test rigorously! Especially the scale part probably doesn't work
		GameElement parent = (GameElement) getParent();
		if(parent != null){
			input = parent.globalToLocal(input);
		}
		
		// Subtract position and scale
		input.x -= getPosition().x * getScale().x;
		input.y -= getPosition().y * getScale().y;

		// Subtract rotation
		double rads = Math.toRadians(getRotation());
		float newX = (float) (input.x * Math.cos(rads) + input.y * Math.sin(rads));
		float newY = (float) (-input.x * Math.sin(rads) + input.y * Math.cos(rads)) ;
		input.x = newX;
		input.y = newY;
		
		
		
		return input;
	}
	
	/**
	 * See {@link #globalToLocal(Vector2f)}
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector2f globalToLocal(float x, float y){
		return globalToLocal(new Vector2f(x, y));
	}
	
	/**
	 * Converts a local coordinate into global coordinate space
	 * @param input - a local coordinate
	 * @return the global coordinates of input
	 */
	public Vector2f localToGlobal(Vector2f input){
		// TODO: Test rigorously! Especially the scale part probably doesn't work
		
		// Subtract rotation
		double rads = Math.toRadians(getRotation());
		float newX = (float) (input.x * Math.cos(rads) - input.y * Math.sin(rads));
		float newY = (float) (input.x * Math.sin(rads) + input.y * Math.cos(rads)) ;
		input.x = newX;
		input.y = newY;
		
		// Subtract position and scale
		input.x += getPosition().x * getScale().x;
		input.y += getPosition().y * getScale().y;
		
		GameElement parent = (GameElement) getParent();
		if(parent != null){
			input = parent.localToGlobal(input);
		}
		return input;
	}
	
	/**
	 * See {@link #localToGlobal(Vector2f)}
	 * @param x
	 * @param y
	 * @return
	 */
	public Vector2f localToGlobal(float x, float y){
		return localToGlobal(new Vector2f(x, y));
	}
	
	/**
	 * See {{@link #hitTestPoint(float, float)}
	 * @param vec
	 * @return
	 */
	public boolean hitTestPoint(Vector2f vec){
		return hitTestPoint(vec.x, vec.y);
	}
	
	/**
	 * Test whether a point is within the bounding box of this object
	 * Remember to convert global coordinates to local coordinates first
	 * using {@link #globalToLocal(Vector2f)}
	 * @param x
	 * @param y
	 * @return true if the point is within the bounding box, else false
	 */
	public boolean hitTestPoint(float x, float y){
		Vector2f pos = getPosition();
		Vector2f dim = getDimensions();
		Vector2f scale = getScale();
		if(x >= pos.x && x <= (pos.x + dim.x * scale.x)
				&& y >= pos.y && y < (pos.y + dim.y * scale.y)){
			return true;
		}
		
		return false;
	}
	
	public Transform getTransform(){
		return transform;
	}
	
	@Override
	public void mouseClicked(int btn, int x, int y, int clickCount) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(int btn, int x, int y) {
		
		
	}
	@Override
	public void mouseReleased(int btn, int x, int y) {
		
		
	}
	@Override
	public void mouseWheelMoved(int change) {
		
		
	}
	
	@Override
	public void inputEnded() {
		
		
	}
	@Override
	public void inputStarted() {
		
		
	}
	@Override
	public boolean isAcceptingInput() {
		return true;
	}
	@Override
	public void setInput(Input input) {

	}

	@Override
	public void keyPressed(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0, char arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonPressed(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerButtonReleased(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerDownReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerLeftReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerRightReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void controllerUpReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
