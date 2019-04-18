/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class SubUserThreadOne extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SubUserThreadOne is complete");
	}

}
