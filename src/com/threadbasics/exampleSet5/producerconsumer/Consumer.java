package com.threadbasics.exampleSet5.producerconsumer;

import java.util.Vector;

/**
 * 
 * @author Anuj Sharma
 *
 */
public class Consumer extends Thread{
	private Vector<String> sharedQueue;

	public Consumer(Vector<String> sharedQueue) {
		this.sharedQueue=sharedQueue;
	}

	@Override
	public void run() {
		synchronized (sharedQueue) {
			while(true){
				while(sharedQueue.size() > 0){
					System.out.println(sharedQueue.remove(0)+ " element has been removed by Consumer Thread "+""+sharedQueue.toString());
				}
				sharedQueue.notifyAll();
				// wait for producer to produce more data
				try {
					while(sharedQueue.size() ==0){
						sharedQueue.wait();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}		
			}//end while loop
		}
	}
}
