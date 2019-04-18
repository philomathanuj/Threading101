/**
 * 
 */
package com.threadbasics.exampleSet1.creationofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class FirstThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("Hello to our first thread !!!! "+Thread.currentThread().getName());
		try {
			Thread.sleep(1);
			System.out.println("Sleeping period over. Wake Up. "+Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
