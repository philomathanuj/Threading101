/**
 * 
 */
package com.threadbasics.exampleSet3.joiningthreads;

/**
 * @author Anuj Sharma
 * 
 * Join method forces the currently running thread to
 * wait until the thread it joins with finishes its execution.
 * 
 *
 */
public class ThreadJoinTest {

	public static void main(String[] args) {
		Thread t1=new Thread(new WorkerThreadOne());
		Thread t2=new Thread(new WorkerThreadTwo());
		Thread t3=new Thread(new WorkerThreadThree());
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("All the worker threads have finished execution");
		
	}
}
