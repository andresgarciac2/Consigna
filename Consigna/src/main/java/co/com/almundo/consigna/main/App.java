package co.com.almundo.consigna.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import co.com.almundo.consigna.model.call.Call;
import co.com.almundo.consigna.model.employee.Director;
import co.com.almundo.consigna.model.employee.Employee;
import co.com.almundo.consigna.model.employee.Operator;

/**
 * Main App
 *
 */
public class App {
	
    public static void main(String[] args) throws InterruptedException {
    	
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		List<Employee> employees = Arrays.asList(new Operator(), new Operator(), new Operator(), new Director());
		
		Consigna.setEmployees(employees);
		
		List<Dispatcher> callables = new ArrayList<>();
		
		for(int i = 0; i <=4; i++) {
			callables.add(new Dispatcher(new Call(i)));
		}

		executorService.invokeAll(callables).stream().map(future -> {
			try {
				return future.get().get();
			} catch (Exception e) {

			}
			return null;
		}).forEach(c -> System.out.println(c.isAttended()));

    }
}
