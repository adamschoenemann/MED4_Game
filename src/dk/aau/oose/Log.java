package dk.aau.oose;

import org.apache.log4j.Logger;

public class Log {
	
	private static final Logger log = Logger.getLogger(Log.class);
	
	
	public static void d(Object message){
		log.debug(message);
	}
	
	public static void e(Object message){
		log.error(message);
	}
}
