package dk.aau.oose;

import org.lwjgl.util.vector.Vector2f;

@Deprecated
public interface IGameElement {
	
	public void draw();
	public void update();
	public void destroy();
	
}
