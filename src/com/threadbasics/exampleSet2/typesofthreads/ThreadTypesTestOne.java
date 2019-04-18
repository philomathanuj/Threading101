/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;


/**
 * @author Anuj Sharma
 * <ul>There are two types of Threads
 * <li>User Threads : User Threads run independently</li>
 * <li>Daemon Threads : Daemon Threads wait for User Threads to complete</li>
 * </ul>
 *
 */
public class ThreadTypesTestOne {
	public static void main(String[] args) {
		Thread userThread=new UserThread();
		userThread.start();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Is User Thread Alive : "+userThread.isAlive());
		
	}

}
