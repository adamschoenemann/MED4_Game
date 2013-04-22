package dk.aau.oose;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import dk.aau.oose.core.SpriteElement;
import dk.aau.oose.core.GameWorld;

public class TestGameElement extends SpriteElement {

	public TestGameElement(Image sprite) {
		super(sprite);
		setPosition(0f, 0f);
	}

	@Override
	public void update() {
		Vector2f pos = getPosition();
		pos.x++;
		pos.y++;
	}
	
	@Override
	public void draw(){
		Vector2f pos = getPosition();
		GameWorld.getGameContainer().getGraphics().drawOval(pos.x, pos.y, 50, 50);

	}

}
