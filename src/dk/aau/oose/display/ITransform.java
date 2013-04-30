package dk.aau.oose.display;

import org.lwjgl.util.vector.Vector2f;

/**
 * Interface for specifying transform functionality - position/rotation/scale
 * @author Adam
 *
 */
public interface ITransform {
	
	/**
	 * 
	 * @return relative position of the transform
	 */
	public Vector2f getPosition();
	/**
	 * 
	 * @param vec - the new position of the transform
	 */
	public void setPosition(Vector2f vec);
	/**
	 * See {@link #setPosition(Vector2f)}
	 * @param x
	 * @param y
	 */
	public void setPosition(float x, float y);
	
	/**
	 * 
	 * @return the rotation in degrees
	 */
	public float getRotation();
	/**
	 * 
	 * @param rot - the rotation in degrees
	 */
	public void setRotation(float rot);
	
	/**
	 * 
	 * @return a vector specifying scale (Default is (1, 1))
	 */
	public Vector2f getScale();
	/**
	 * 
	 * @param vec - the new scale vector
	 */
	public void setScale(Vector2f vec);
	/**
	 * See {@link #setScale(Vector2f)}
	 * @param w - the width
	 * @param h - the height
	 */
	public void setScale(float w, float h);
	
}
