/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class SubUserThreadTwo extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("SubUserThreadTwo is complete");
	}

}
