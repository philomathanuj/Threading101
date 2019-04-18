/**
 * 
 */
package com.threadbasics.exampleSet1.creationofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class SecondThread implements Runnable{

	@Override
	public void run() {
		System.out.println("Hello World with Runnable Thread !!! "+Thread.currentThread().getName());
	}

}
