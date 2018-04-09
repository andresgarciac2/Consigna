package co.com.almundo.consigna.main;

import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import co.com.almundo.consigna.constants.Constants;
import co.com.almundo.consigna.model.call.Call;
import co.com.almundo.consigna.model.employee.Director;
import co.com.almundo.consigna.model.employee.Employee;
import co.com.almundo.consigna.model.employee.Operator;
import co.com.almundo.consigna.model.employee.Supervisor;

/**
 * 
 * The distpach class manage a call, could be called by multiple threads 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class Dispath implements Callable<Future<Call>>{
	
	/**
	 * The ExecutorService
	 */
	static ExecutorService executor = Executors.newFixedThreadPool(Constants.THREADS);
	
	 /**
	  * The call
	  */
	Call call = null;
	
	/**
	 * The Constructor
	 * 
	 * @param call
	 */
	public Dispath(Call call) {
		
		this.call = call;
	}
	
	/**
	 * dispatchCall Method, distpatch a call 
	 * 
	 * @param call
	 * @return a Future with the call execution
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public Future<Call> dispatchCall(Call call) throws InterruptedException, ExecutionException {

		Employee employee = getAvailableEmployee();
		Future<Call> result = null;

		if (employee != null) {

			result = executor.submit(() -> {
				employee.answerCall(call);
				return call;
			});
		} else {
			
			Consigna.getPendingCalls().add(call);
			TimeUnit.SECONDS.sleep(Constants.CALL_WAITING_TIME_IN_SEC);
			result = dispatchCall(Consigna.getCallByPriority());
		}

		return result;
	}
	
	/**
	 * Main method for execute the thread
	 */
	@Override
	public Future<Call> call() throws Exception {

		return dispatchCall(this.call);
	}
	
	/**
	 * Get the available employee for answer the call
	 * 
	 * @return the employee
	 */
	private synchronized Employee getAvailableEmployee() {
		
		Optional<Employee> employee;
		
		employee = Consigna.getEmployees().stream()
			.filter(operator -> operator instanceof Operator && operator.isAvailable())
			.findFirst();
		
		if (employee.isPresent()) {
			
			employee.get().setAvailable(false);
			return employee.get();
		}
		
		employee = Consigna.getEmployees().stream()
			.filter(supervisor -> supervisor instanceof Supervisor && supervisor.isAvailable())
			.findFirst();
		
		if (employee.isPresent()) {
			
			employee.get().setAvailable(false);
			return employee.get();
		}
		
		employee = Consigna.getEmployees().stream()
			.filter(director -> director instanceof Director && director.isAvailable())
			.findFirst();
			
		if (employee.isPresent()) {
				
			employee.get().setAvailable(false);
			return employee.get();
		}
		
		return null;
	}
	
}
