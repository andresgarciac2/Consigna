package co.com.almundo.consigna.model.employee;

import co.com.almundo.consigna.model.call.Call;

/**
 * 
 * The Employee 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public abstract class Employee {
	
	/**
	 * the call
	 */
	protected Call call;
	
	/**
	 * is available
	 */
	protected Boolean available = true;
	
	/**
	 * Answer the call
	 * 
	 * @param call
	 */
	public abstract void answerCall(Call call);
	
	/**
	 * The employee availability
	 * 
	 * @return is available
	 */
	public Boolean isAvailable() {
		return this.available;
	}
	
	/**
	 * set the employee availability
	 * 
	 * @param available
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
}
