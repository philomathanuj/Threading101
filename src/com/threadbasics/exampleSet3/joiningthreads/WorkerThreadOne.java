/**
 * 
 */
package com.threadbasics.exampleSet3.joiningthreads;

/**
 * @author Anuj Sharma
 *
 */
public class WorkerThreadOne implements Runnable{

	@Override
	public void run() {
		// Task 1: Collateral Valuation
		try {
			Thread.sleep(1000);
			System.out.println("Collateral Valuation is complete");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
