package com.javaconcurrencyutil.exampleSet12.callableandfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Anuj Sharma Callable interface has two advantages over run method of
 *         Runnable 1. It can return values. These values are called Future objects.
 *         Because there value will not be available immediately but at a later
 *         point in time in future. 2.It can throw checked exceptions.
 * 
 *         Future provides cancel() method to cancel the associated Callable
 *         task. There is an overloaded version of get() method where we can
 *         specify the time to wait for the result, it’s useful to avoid current
 *         thread getting blocked for longer time. There are isDone() and
 *         isCancelled() methods to find out the current status of associated
 *         Callable task.
 *
 */
public class CallableAndFutureTest {
	
	public static void main(String[] args) {
		CallableAndFutureTest test=new CallableAndFutureTest();
		ExecutorService service=Executors.newFixedThreadPool(3);
		Future<Integer> sumPartOne=service.submit(test.new SumCalculatorOne());
		Future<Integer> sumPartTwo=service.submit(test.new SumCalculatorTwo());
		Future<Integer> sumPartThree=service.submit(test.new SumCalculatorThree());
		
		try {
			int sum=sumPartOne.get()+sumPartTwo.get()+sumPartThree.get();
			System.out.println("Sum Total : "+sumPartOne.get()+" +  "+sumPartTwo.get()+" + "+sumPartThree.get()+"= "+ sum);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		service.shutdown();
		
	}
	
	public class SumCalculatorOne implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			int sum=0;
			for (int i = 1; i <= 5; i++) {
				sum=sum+i;
			}
			Thread.sleep(1000);
			return sum;
		}
		
	}
	
	public class SumCalculatorTwo implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			int sum=0;
			for (int i = 6; i <= 9; i++) {
				sum=sum+i;
			}
			Thread.sleep(2000);
			return sum;
		}
		
	}
	
	public class SumCalculatorThree implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			int sum=0;
			for (int i = 10; i <= 12; i++) {
				sum=sum+i;
			}
			Thread.sleep(3000);
			return sum;
		}
		
	}

}
