/**
 * 
 */
package com.threadbasics.exampleSet3.joiningthreads;

/**
 * @author Anuj Sharma
 *
 */
public class WorkerThreadThree implements Runnable {
	Thread pendingThread;
	public WorkerThreadThree(Thread w2) {
		pendingThread=w2;
	}

	public WorkerThreadThree() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			pendingThread.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Task 3: Application Verification
		try {
			Thread.sleep(1000);
			System.out.println("Application Verification Complete");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
