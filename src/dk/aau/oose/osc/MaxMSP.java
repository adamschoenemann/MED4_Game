package dk.aau.oose.osc;

import java.util.ArrayList;

import netP5.NetAddress;
import oscP5.OscMessage;
import oscP5.OscP5;

public class MaxMSP {	
	private NetAddress maxLocation;
	private OscP5 oscP5;
	
	/**
	 * Message listeners
	 */
	private ArrayList<MaxMSPListener> listeners;
	
	/**
	 * Singleton instance
	 */
	private static MaxMSP instance;
	
	/**
	 * Connect to MaxMSP
	 * @param adress computer's address
	 * @param port application's port
	 */
	public static void Connect(String adress,int port){
		if (instance != null)
			instance.oscP5.disconnect(instance.maxLocation);
		instance = new MaxMSP(adress, port);
	}
	
	/**
	 * Sends an OSC message to MaxMSP
	 * @param the message to be sent
	 */
	public static void send(OscMessage message){
		if (instance != null)
			instance.oscP5.send(message,instance.maxLocation);
	}
	
	/**
	 * Sends a string to MaxMSP
	 * @param the message to be sent
	 */
	public static void send(String label, String str){
		if (instance != null){
			OscMessage m = new OscMessage(label);
			m.add(str);
			instance.oscP5.send(m,instance.maxLocation);
		}
	}
	
	/**
	 * Sends an integer to MaxMSP
	 * @param the message to be sent
	 */
	public static void send(String label, int n){
		if (instance != null){
			OscMessage m = new OscMessage(label);
			m.add(n);
			instance.oscP5.send(m,instance.maxLocation);
		}
	}
	
	/**
	 * Add a listener to MaxMSP
	 * @param listener the listener object
	 */
	public static void addListener(MaxMSPListener listener){
		if (instance != null)
			instance.listeners.add(listener);
	}
	
	/**
	 * Remove a listener from MaxMSP
	 * @param listener the listener object
	 */
	public static void removeListener(MaxMSPListener listener){
		if (instance != null)
			instance.listeners.remove(listener);
	}
	
	/**
	 * Private constructor for the singleton
	 * @param address
	 * @param port
	 */
	private MaxMSP(String address,int port){
		maxLocation = new NetAddress(address,port);
		oscP5 = new OscP5(this,port);
		listeners = new ArrayList<MaxMSPListener>();
	}
	
	/**
	 * Message dispatch
	 * @param theOscMessage
	 */
	void oscEvent(OscMessage theOscMessage) {
		for (MaxMSPListener listener:listeners)
			listener.messageReceived(theOscMessage);
	}
}
