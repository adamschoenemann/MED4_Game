package dk.aau.oose.util;

public class MathUtils {
	
	public static double degToRad(double deg){
		return (deg / 180.0) * Math.PI;
	}
	
	public static double radToDeg(double rad){
		return rad / Math.PI * 180;
	}
	
}
