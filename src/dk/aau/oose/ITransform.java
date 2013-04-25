package dk.aau.oose;

import org.lwjgl.util.vector.Vector2f;

public interface ITransform {
	public Vector2f getPosition();
	public void setPosition(Vector2f pos);
	public Vector2f getDimensions();
	public void setDimensions(Vector2f vec);
}
