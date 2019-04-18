/**
 * 
 */
package com.javaconcurrencyutil.exampleSet9.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author Anuj Sharma
 * CountDownLatch is a synchronization aid which allows one or more threads to wait until a set of
 * operations in other threads has completed.
 * Each worker thread will decrement the latch by one after it finishes its task.
 * Once all the threads are done with their execution, latch value becomes zero. This serves as the condition
 * for waiting threads to resume their operations.
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch latch=new CountDownLatch(3);
		CountDownLatchTest test=new CountDownLatchTest();
		Thread[] workerThreads={test.new WorkerThreadOne(latch), 
				test.new WorkerThreadTwo(latch),
				test.new WorkerThreadThree(latch)};
		for (int i = 0; i < workerThreads.length; i++) {
			workerThreads[i].start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting Main Thread");
	}

	public  class WorkerThreadOne extends Thread{
		CountDownLatch latch;
		public WorkerThreadOne(CountDownLatch latch) {
			this.latch=latch;
		}
		@Override
		public void run() {
			System.out.println("First Task is complete");
			latch.countDown();
		}
	}

	public class WorkerThreadTwo extends Thread{
		CountDownLatch latch;
		public WorkerThreadTwo(CountDownLatch latch) {
			this.latch=latch;
		}
		@Override
		public void run() {
			System.out.println("Second Task is complete");
			latch.countDown();
		}
	}

	public class WorkerThreadThree extends Thread{
		CountDownLatch latch;
		public WorkerThreadThree(CountDownLatch latch) {
			this.latch=latch;
		}
		@Override
		public void run() {
			System.out.println("Third Task is complete");
			latch.countDown();
		}
	}
}

