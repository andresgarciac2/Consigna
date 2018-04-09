package co.com.almundo.consigna.model.call;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * The Call Unit test 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class CallTest {

	@Test
	public void doCallTest() {
		
		Call call = new Call(30033);
		call.answer();
		
		assertTrue(call.isAttended());
	}
	
}
