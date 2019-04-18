/**
 * 
 */
package com.threadbasics.exampleSet5.producerconsumer;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anuj Sharma
 * Below program is a solution of classic Producer Consumer problem.
 * There two points to be noted:
 * 1. Message Queue is shared between Producer and Consumer Threads.
 * 2. Consumer should not try to read data from message queue when it's empty. In this case, it will wait
 * for producer to produce some data in the message queue.
 * 3. Producer should not write data into message queue when it's full. In this case producer shall wait
 * for the data to be removed by Consumer.
 *
 */
public class ProducerConsumerTest {
	
	public static void main(String[] args) {
		Vector<String> messageQueue=new Vector<String>(5);
		AtomicInteger MAX_SIZE=new AtomicInteger(5);
		Producer producerThread=new Producer(messageQueue,MAX_SIZE);
		Consumer consumerThread=new Consumer(messageQueue);
		producerThread.start();
		consumerThread.start();
	}

}
