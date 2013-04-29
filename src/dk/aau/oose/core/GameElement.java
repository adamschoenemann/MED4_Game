package dk.aau.oose.core;



import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;

import dk.aau.oose.container.Container;
import dk.aau.oose.display.IDrawable;
import dk.aau.oose.display.ITransform;
import dk.aau.oose.display.Transform;
import dk.aau.oose.util.MathUtils;

/**
 * Basic game content element
 * @author Paolo Burelli
 */
public abstract class GameElement extends Container<GameElement> implements IDrawable, ITransform {

	private final Transform transform = new Transform();
	private final Vector2f dimensions = new Vector2f();
	
	public abstract void onUpdate();
	public abstract void onDraw(Graphics gfx);
	
	public final void update(){
		this.onUpdate();
		for(int i = 0; i < numChildren(); i++){
			getChildAt(i).update();
		}
	}
	
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

//		Vector2f globalPos = localToGlobal(new Vector2f(100, 0));
//		gfx.setColor(Color.green);
//		gfx.drawRect(globalPos.x, globalPos.y, 1f, 1f);
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
	
	public Vector2f globalToLocal(Vector2f input){
		
		GameElement parent = (GameElement) getParent();
		if(parent != null){
			input = parent.globalToLocal(input);
		}
		
		// Subtract position and scale
		input.x -= getPosition().x * getScale().x;
		input.y -= getPosition().y * getScale().y;

		// Subtract rotation
		double rads = MathUtils.degToRad(getRotation());
		float newX = (float) (input.x * Math.cos(rads) + input.y * Math.sin(rads));
		float newY = (float) (-input.x * Math.sin(rads) + input.y * Math.cos(rads)) ;
		input.x = newX;
		input.y = newY;
		
		
		
		return input;
	}
	
	public Vector2f localToGlobal(Vector2f input){
		
		// Subtract rotation
		double rads = MathUtils.degToRad(getRotation());
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
	
	public Transform getTransform(){
		return transform;
	}
	
	
}
