/**
 * 
 */
package com.threadbasics.exampleSet5.producerconsumer;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anuj Sharma
 *
 */
public class Producer extends Thread{

	public Vector<String> sharedQueue;
	public AtomicInteger maxSize;

	public Producer(Vector<String> sharedQueue,AtomicInteger size) {
		this.sharedQueue=sharedQueue;
		this.maxSize=size;
	}
	@Override
	public void run() {
		int counter =0;
		synchronized(sharedQueue){
			while(counter<10){
				if(sharedQueue.size() == maxSize.get()){
					try{
						sharedQueue.wait();
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				}
				sharedQueue.addElement(String.valueOf(counter++));
				System.out.println("Queue after add by Producer:"+sharedQueue.toString());
			}
			sharedQueue.notify();
		}
	}
}
