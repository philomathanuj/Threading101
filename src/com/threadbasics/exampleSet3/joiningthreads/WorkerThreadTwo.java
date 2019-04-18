/**
 * 
 */
package com.threadbasics.exampleSet3.joiningthreads;

/**
 * @author Anuj Sharma
 *
 */
public class WorkerThreadTwo implements Runnable{
	Thread pendingThread;
	public WorkerThreadTwo(Thread w1) {
		pendingThread=w1;
	}

	public WorkerThreadTwo() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			pendingThread.join();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}// will wait for w1 to complete
		// Task 2: Perform Collateral Title Search
		try {
			Thread.sleep(1000);
			System.out.println("Collateral Title Search is Complete");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
