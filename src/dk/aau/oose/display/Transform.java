package dk.aau.oose.display;

import org.lwjgl.util.vector.Vector2f;

public class Transform implements ITransform {
	
	private Vector2f position = new Vector2f(), scale = new Vector2f(1f, 1f);
	private float rotation = 0f;
	
	@Override
	public Vector2f getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2f vec) {
		position.x = vec.x;
		position.y = vec.y;
	}

	@Override
	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}

	@Override
	public float getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(float rot) {
		rotation = rot;
	}

	@Override
	public Vector2f getScale() {
		return scale;
	}

	@Override
	public void setScale(Vector2f vec) {
		scale.x = vec.x;
		scale.y = vec.y;
	}

	@Override
	public void setScale(float w, float h) {
		scale.x = w;
		scale.y = h;
	}

}
