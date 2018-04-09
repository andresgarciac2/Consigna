package co.com.almundo.consigna.main;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import co.com.almundo.consigna.model.call.Call;
import co.com.almundo.consigna.model.employee.Director;
import co.com.almundo.consigna.model.employee.Employee;
import co.com.almundo.consigna.model.employee.Operator;
import co.com.almundo.consigna.model.employee.Supervisor;

/**
 * 
 * The Dispath Unit test 
 * 
 * @author Andres Garcia
 * @version 0.0.1
 * @date 8/04/20148
 *
 */
public class DispatcherTest {
	/**
	 * the executorService
	 */
	ExecutorService executorService;
	
	/**
	 * the employees
	 */
	List<Employee> employees;

	/**
	 * the callables
	 */
	List<Dispatcher> callables;
	
	@Before
	public void initEnviroment() {
		
		executorService = Executors.newFixedThreadPool(10);
	}
	
	/**
	 * The required test 
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void requiredTestTenCalls() throws InterruptedException {

		tenCallsAndTenEmployeesSetUp();

		executorService.invokeAll(callables).stream().map(future -> {

			try {
				return future.get().get();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (ExecutionException e) {

				e.printStackTrace();
			}
			return null;

		}).forEach(c -> assertTrue(c.isAttended()));
		
		executorService.shutdown();
	}
	
	@Test
	public void fiveCallsThreeEmployeesTest() throws InterruptedException {

		fiveCallsAndThreeEmployeesSetUp();

		executorService.invokeAll(callables).stream().map(future -> {

			try {
				return future.get().get();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (ExecutionException e) {

				e.printStackTrace();
			}
			return null;

		}).forEach(c -> assertTrue(c.isAttended()));
		
		executorService.shutdown();
	}

	@Test
	public void threeCallsFiveEmployeesTest() throws InterruptedException {

		threeCallsAndFiveEmployeesSetUp();

		executorService.invokeAll(callables).stream().map(future -> {

			try {
				return future.get().get();
			} catch (InterruptedException e) {

				e.printStackTrace();
			} catch (ExecutionException e) {

				e.printStackTrace();
			}
			return null;

		}).forEach(c -> assertTrue(c.isAttended()));
		
		executorService.shutdown();
	}
	
	private void tenCallsAndTenEmployeesSetUp() {
		
		employees = Arrays.asList(
				new Operator(), 
				new Operator(), 
				new Operator(), 
				new Operator(), 
				new Operator(), 
				new Operator(),
				new Operator(), 
				new Operator(), 
				new Supervisor(),
				new Director());
		
		Consigna.setEmployees(employees);
		callables = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) {
			callables.add(new Dispatcher(new Call(i)));
		}
	}
	
	private void fiveCallsAndThreeEmployeesSetUp() {
		
		employees = Arrays.asList(
				new Operator(), 
				new Operator(),
				new Operator());
		
		Consigna.setEmployees(employees);
		callables = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			callables.add(new Dispatcher(new Call(i)));
		}
	}
	
	private void threeCallsAndFiveEmployeesSetUp() {
		
		employees = Arrays.asList(
				new Operator(), 
				new Operator(),
				new Operator(),
				new Supervisor(),
				new Director());
		
		Consigna.setEmployees(employees);
		callables = new ArrayList<>();
		
		for(int i = 0; i < 3; i++) {
			callables.add(new Dispatcher(new Call(i)));
		}
	}
	
}
