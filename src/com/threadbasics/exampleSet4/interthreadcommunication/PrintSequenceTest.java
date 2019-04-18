package com.threadbasics.exampleSet4.interthreadcommunication;
/**
 * Prints 1 to 10 in sequence using 2 threads.
 * 
 */
import java.util.concurrent.atomic.AtomicInteger;

public class PrintSequenceTest {

	public static void main(String[] args) {
		Object sharedLock = new Object();
		AtomicInteger counter = new AtomicInteger(0);
		
		Thread evenThread = new EvenPrintingThread(sharedLock, counter);
		evenThread.start();
		
		
		
		synchronized (sharedLock) {
			while (counter.get() < 10) {
				if (counter.get() % 2 == 0) {
					counter.set(counter.get()+1);
					System.out.print(counter.get()+" ");
					sharedLock.notify();
					try {
						sharedLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else{
					sharedLock.notify();
					try {
						sharedLock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			sharedLock.notify();
		}
		
		
	}

}
