package co.com.almundo.consigna.main;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import co.com.almundo.consigna.model.call.Call;
import co.com.almundo.consigna.model.employee.Employee;

/**
 * 
 * This class manage the employees list and the Queued calls
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class Consigna {

	/**
	 * The employees
	 */
	static List<Employee> employees;
	
	/**
	 * The pending calls queue
	 */
	static Queue<Call> pendingCalls = new LinkedList<Call>();
	
	/**
	 * Set the employees list
	 * 
	 * @param employeesList
	 */
	public static void setEmployees(List<Employee> employeesList) {
		employees = Collections.synchronizedList(employeesList);
	}
	
	/**
	 * get the employees list
	 * 
	 * @return the employees
	 */
	public static List<Employee> getEmployees() {
		return employees;
	}
	
	/**
	 * get the pending calls
	 * 
	 * @return the not answered calls
	 */
	public static Queue<Call> getPendingCalls() {

		return pendingCalls;
	}
	
	/**
	 * get the call in the queue's head
	 * 
	 * @return a call
	 */
	public synchronized static Call getCallByPriority() {

		return pendingCalls.poll();
	}
}
