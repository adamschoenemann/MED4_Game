package dk.aau.oose.core;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;

import dk.aau.oose.IGameElement;

/**
 * Basic game content element
 * @author Paolo Burelli
 */
public abstract class SpriteElement implements IGameElement {
	/**
	 * Object's life status
	 */
	private boolean alive = true;
	
	/**
	 * Object's center position
	 */
	protected Vector2f position;
	
	/**
	 * Object's size
	 */
	protected Vector2f size;
	
	/**
	 * Object's sprite
	 */
	private Image sprite;
	
	/**
	 * Object constructor
	 * @param sprite Image to be displayed for the object
	 */
	public SpriteElement(Image sprite) {
		this.sprite = sprite;
		size = new Vector2f(sprite.getWidth(),sprite.getHeight());
	}

	/**
	 * Check if the object is still alive
	 * @return true if alive
	 */
	public boolean isAlive(){
		return alive;
	}

	/**
	 * Set the object as dead and removes it from the game
	 */
	public void destroy(){
		alive = false;
		GameWorld.remove(this);
	}
	
	/**
	 * Returns true if this object collides with the other one
	 * @param other the object with which the collision is checked
	 * @return true if there is a collision
	 */
	public boolean collides(SpriteElement other){
		return  (this.position.x - this.size.x/2 < other.position.x + other.size.x/2) && 
	            (other.position.x - other.size.x/2 < this.position.x + this.size.x/2) &&
	            (this.position.y - this.size.y/2 < other.position.y + other.size.y/2) &&
	            (other.position.y - other.size.y/2 < this.position.y + this.size.y/2);
	}
	
	/**W
	 * Object's draw function, draws the object's sprite in its current position
	 */
	public void draw() {
		sprite.draw(position.x-sprite.getWidth()/2, position.y-sprite.getHeight()/2);
	}
	
	public Vector2f getPosition(){
		return position;
	}
	
	public void setPosition(Vector2f pos){
		position = pos;
	}
	
	public void setPosition(float x, float y){
		setPosition(new Vector2f(x, y));
	}
	
	/**
	 * Implement this method to define the object's behavior
	 */
	public abstract void update();

}
