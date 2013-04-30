package dk.aau.oose.util;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Derivative {
	
	private float value = 0f;
	private float deriv = 0f;
	
	public static interface OnChangeCallback {
		public void call(float val, float newVal);
	}
	
	private OnChangeCallback callback;
	
	public Derivative(float value){
		this.value = value;
	}
	
	public final void setValue(float newVal){
		if(value != newVal){
			onChange(value, newVal);
		}
		this.value = newVal;
	}
	
	protected void onChange(float val, float newVal) {
		deriv = val - newVal;
		if(callback != null){
			callback.call(val, newVal);
		}
		
	}

	public final float getValue(){
		return value;
	}
	
	public void setOnChangeCallback(OnChangeCallback cb){
		callback = cb;
	}
	
	public final float getDerivative(){
		return deriv;
	}
	
	/////////////////// TEST //////////////////////////
	
	
	public static void main(String[] args){
		
		final int SIZE = 10;
		
		
		float[] derivs = new float[SIZE];
		float[] numbers = new float[SIZE];
		
		Derivative dev = new Derivative(10f);
		for(int i = 0; i < SIZE; i++){
			numbers[i] = (float) Math.random() * 100;
			dev.setValue(numbers[i]);
			derivs[i] = dev.getDerivative();
			System.out.println(derivs[i] + "\t|\t" + numbers[i]);
		}
		
		SimpleGraph frame = new SimpleGraph(derivs);
		
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

	
}

class SimpleGraph extends JFrame {
	
	float[] data;
	
	public SimpleGraph(float[] data){
		super();
		this.data = data;
	}

	@Override
	public void paint(Graphics g){
		for(int i = 0; i < data.length; i++){
			g.drawOval(20 + i * ((getWidth() - 20) / data.length), (int) (this.getHeight()/2 - data[i] * 1), 5, 5);
		}
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
	}

}
