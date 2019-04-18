package com.javaconcurrencyutil.exampleSet11.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Anuj Sharma The java.util.concurrent.ExecutorService interface
 *         represents an asynchronous execution mechanism which is capable of
 *         executing tasks in the background. An ExecutorService is thus very
 *         similar to a thread pool. In fact, the implementation of
 *         ExecutorService present in the java.util.concurrent package is a
 *         thread pool implementation. 
 *         newFixedThreadPool: Creates a thread pool
 *         that reuses a fixed number of threads operating off a shared
 *         unbounded queue. At any point, at most nThreads threads will be
 *         active processing tasks. If additional tasks are submitted when all
 *         threads are active, they will wait in the queue until a thread is
 *         available. If any thread terminates due to a failure during execution
 *         prior to shutdown, a new one will take its place if needed to execute
 *         subsequent tasks. The threads in the pool will exist until it is
 *         explicitly shutdown.
 * 
 *         newCachedThreadPool: Creates a thread pool that creates new threads
 *         as needed, but will reuse previously constructed threads when they
 *         are available. These pools will typically improve the performance of
 *         programs that execute many short-lived asynchronous tasks. Calls to
 *         execute will reuse previously constructed threads if available. If no
 *         existing thread is available, a new thread will be created and added
 *         to the pool. Threads that have not been used for sixty seconds are
 *         terminated and removed from the cache. Thus, a pool that remains idle
 *         for long enough will not consume any resources. Note that pools with
 *         similar properties but different details (for example, timeout
 *         parameters) may be created using ThreadPoolExecutor constructors.
 *
 */
public class ExecutorServiceTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);
		ExecutorService serviceCached = Executors.newCachedThreadPool();
		serviceCached.execute(new Thread() {
			@Override
			public void run() {
				System.out.println("Cached Thread has run");
			}
		});
		service.execute(new Thread() {
			@Override
			public void run() {
				System.out.println("Task One has Run");
			}
		});

		service.execute(new Thread() {
			@Override
			public void run() {
				System.out.println("Task Two has Run");
			}
		});

		service.execute(new Thread() {
			@Override
			public void run() {
				System.out.println("Task Three has Run");
			}
		});

		try {
			service.awaitTermination(2, TimeUnit.SECONDS);
			serviceCached.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();
		serviceCached.shutdown();
	}

}
