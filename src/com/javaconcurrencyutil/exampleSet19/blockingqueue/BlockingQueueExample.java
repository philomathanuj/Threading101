package com.javaconcurrencyutil.exampleSet19.blockingqueue;
/*
 * methods of BlockingQueue are put() and take() which are blocking methods in Java and used to insert and retrive elements 
 * from BlockingQueue in Java. 
 * put() will block if BlockingQueue is full and take() will block if BlockingQueue is empty, 
 * call to take() removes element from head of Queue 
 * 
 */
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {
	public static void main(String args[]){
		BlockingQueue bq = new ArrayBlockingQueue(1000);
		
		Producer produce = new Producer(bq);
		Consumer consume = new Consumer(bq);
		new Thread(produce).start();
		new Thread(consume).start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
