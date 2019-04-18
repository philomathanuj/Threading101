package com.threadbasics.exampleSet1.creationofthreads;


/**
 * There are two ways to create a thread :
 * <ul>
 * <li> By extending java.lang.Thread Class</li>
 * <li>By passing an implementation of Runnable interface to Thread constructor</li>
 * <li>There is no guarantee that all the spawned threads will executed in the order in which they
 * were started.</li>
 * </ul>
 */
public class BasicThreadTest {

	public static void main(String[] args) {
		// First thread extends java.lang.Thread class
		Thread firstThread=new FirstThread();
		firstThread.setName("Ketki");
		firstThread.start();
		// start method will internally call run method
		System.out.println("After starting first thread "+Thread.currentThread().getName());

		// Second thread passes implementation of runnable interface
		SecondThread sc = new SecondThread();
		Thread secondThread=new Thread(sc);
		secondThread.setName("Anuj");
		secondThread.start();
		System.out.println("After starting second thread "+Thread.currentThread().getName());

		try {
			secondThread.join();
		  }catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Main Method continues while other threads are performing their respective operations
		System.out.println("Main Thread is Exiting "+Thread.currentThread().getName());
	}

}
