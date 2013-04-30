package dk.aau.oose.util;

public class MathUtils {
	

	//Used to test getValueOnLine
	/*public static void main(String[] args){
		System.out.println(getValueOnLine( 1, 0, 0, 2, 2)); // Should be 1
		System.out.println(getValueOnLine( 3, 0, 0, 2, 2)); // Should be 3
		System.out.println(getValueOnLine( 1, 0, 0, 2, 0)); // Should be 0
		System.out.println(getValueOnLine(-1, 0, 0, 2, 2)); // Should be -1
	}*/
	
	/**
	 * @param x The value for which a y value must be returned.
	 * @param x1 The first coordinate in a point on the line.
	 * @param y1 The second coordinate in a point on the line.
	 * @param x2 The first coordinate in another point on the line.
	 * @param y2 The second coordinate in another point on the line.
	 * @return The y value of the point (x,y) on the line defined by the parameters.
	 */
	public static double getValueOnLine(double x, double x1, double y1, double x2, double y2){
/*		double rangeX = x2 - x1;
		double rangeY = y2 - y1;
		double factor = (x - x1) / rangeX;
		double y = y1 + (factor * rangeY);
		return y;	
*/
		return y1 + (((x - x1) / (x2 - x1)) * (y2 - y1));
		
	}
	
	/**
	 * @param x The value for which a y value must be returned.
	 * @param x1 The first coordinate in a point on the line.
	 * @param y1 The second coordinate in a point on the line.
	 * @param x2 The first coordinate in another point on the line.
	 * @param y2 The second coordinate in another point on the line.
	 * @return The y value of the point (x,y) on the line defined by the parameters.
	 */
	public static int getValueOnLine(int x, int x1, int y1, int x2, int y2){
/*		double rangeX = x2 - x1;
		double rangeY = y2 - y1;
		double factor = (x - x1) / rangeX;
		double y = y1 + (factor * rangeY);
		return y;	
*/
		return Math.round((float)y1 + (((float)(x - x1) / (float)(x2 - x1)) * (float)(y2 - y1)));
		
	}
	
}
