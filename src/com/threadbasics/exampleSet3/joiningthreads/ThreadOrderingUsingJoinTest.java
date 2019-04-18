/**
 * 
 */
package com.threadbasics.exampleSet3.joiningthreads;

/**
 * @author Anuj Sharma
 * In this program we are going to ensure that WorkerThread1 is completed first followed by WorkerThread2
 * and then work thread 3
 *
 */
public class ThreadOrderingUsingJoinTest {
	
	public static void main(String[] args) {
		
		
		Thread w1=new Thread(new WorkerThreadOne());
		Thread w2=new Thread(new WorkerThreadTwo(w1));
		Thread w3=new Thread(new WorkerThreadThree(w2));
		

		w1.start();
		w2.start();
		w3.start();
		try {
			w1.join();
			w2.join();
			w3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Main Thread has finished its execution");
	}

}
