/**
 * 
 */
package com.javaconcurrencyutil.exampleSet7.reentrantlock;


/**
 * @author Anuj Sharma
 *
 */
public class LockTest {
	public static void main(String[] args) {
		Thread updateThread=new UpdateRecordThread();
		updateThread.start();
	}
	

}
