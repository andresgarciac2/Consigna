package co.com.almundo.consigna.model.call;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import co.com.almundo.consigna.constants.Constants;

/**
 * 
 * The Call 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class Call{

	/**
	 * the UUID
	 */
	UUID uuid = UUID.randomUUID();
	
	/**
	 * The from number
	 */
	Integer number;
	
	/**
	 * Was attended attr
	 */
	Boolean attended = false;
	
	/**
	 * The constructor
	 * 
	 * @param number
	 */
	public Call (Integer number) {
		this.number = number;
	}
	
	/**
	 * Answer the call and gives a duration
	 */
	public void answer() {
		
		int duration = ThreadLocalRandom.current()
			.nextInt(Constants.MIN_CALL_TIME_IN_SEC, Constants.MAX_CALL_TIME_IN_SEC);
		
		try {
			TimeUnit.SECONDS.sleep(duration);
			this.attended = true;
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	
	/**
	 * If the call was attended
	 * 
	 * @return attended
	 */
	public Boolean isAttended() {
		return this.attended;
	}

}