package dk.aau.oose.tests;

import dk.aau.oose.util.MathUtils;

public class TestMathUtilsScale {
	
	public static void main(String[] args){
		
		System.out.println(MathUtils.scale(5.0, 0.0, 10.0, 0.0, 10.0)); //Should be 5.0
		System.out.println(MathUtils.scale(5.0, 0.0, 10.0, 10.0, 0.0)); //Should be 5.0
		System.out.println(MathUtils.scale(3.0, 0.0, 10.0, 10.0, 0.0)); //Should be 7.0
		System.out.println(MathUtils.scale(1.0, 0.0, 2.0, 20.0, 0.0)); //Should be 10.0
		System.out.println(MathUtils.scale(-1.0, 0.0, 2.0, 20.0, 0.0)); //Should be 30.0
		
		System.out.println(MathUtils.scale(-0.5, -1.0, 0.0, 0.0, 100.0)); //Should be 50.0
		System.out.println(MathUtils.scale(-1.0, -1.0, 0.0, 0.0, 100.0)); //Should be 0
	}
	
}
