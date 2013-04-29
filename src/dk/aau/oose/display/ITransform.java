package dk.aau.oose.display;

import org.lwjgl.util.vector.Vector2f;

public interface ITransform {
	
	public Vector2f getPosition();
	public void setPosition(Vector2f vec);
	public void setPosition(float x, float y);
	
	public float getRotation();
	public void setRotation(float rot);
	
	public Vector2f getScale();
	public void setScale(Vector2f vec);
	public void setScale(float w, float h);
	
}
