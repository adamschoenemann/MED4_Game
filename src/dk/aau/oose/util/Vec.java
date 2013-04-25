package dk.aau.oose.util;

import org.lwjgl.util.vector.Vector2f;

public class Vec {
	
	public static Vector2f subtract(Vector2f a, Vector2f b){
		return new Vector2f(a.x - b.x, a.y - b.y);
	}
	
	public static Vector2f add(Vector2f a, Vector2f b){
		return new Vector2f(a.x + b.x, a.y + b.y);
	}
	
	public static Vector2f dot(Vector2f a, Vector2f b){
		return new Vector2f(a.x * b.x, a.y * b.y);
	}
	
}
