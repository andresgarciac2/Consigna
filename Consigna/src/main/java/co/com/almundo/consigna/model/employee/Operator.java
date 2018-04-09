package co.com.almundo.consigna.model.employee;

import co.com.almundo.consigna.model.call.Call;

/**
 * 
 * The Operator 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class Operator extends Employee{

	/**
	 * Answer the call
	 * 
	 * @param call
	 */
	@Override
	public void answerCall(Call call) {
		
		this.call = call;
		call.answer();
		this.setAvailable(true);
	}

}
