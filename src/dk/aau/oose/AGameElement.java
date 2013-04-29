package dk.aau.oose;

import org.lwjgl.util.vector.Vector2f;

import dk.aau.oose.core.GameWorld;

@Deprecated
@SuppressWarnings
public abstract class AGameElement implements IGameElement, ITransform {
	
	private final Vector2f pos = new Vector2f(0, 0);
	private final Vector2f dim = new Vector2f(0, 0);
	
	@Override
	public Vector2f getPosition() {
		return pos;
	}

	@Override
	public void setPosition(Vector2f pos) {
		this.pos.x = pos.x;
		this.pos.y = pos.y;
	}

	@Override
	public void destroy(){
		GameWorld.remove(this);
	}
	
	@Override
	public void setDimensions(Vector2f vec){
		dim.x = vec.x;
		dim.y = vec.y;
	}
	
	@Override
	public Vector2f getDimensions(){
		return dim;
	}
}
