/**
 * 
 */
package com.javaconcurrencyutil.exampleSet15.threadpool;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Anuj Sharma
 *
 */
public class ThreadPool {
	private boolean isShutDown;
	private BlockingQueue<Runnable> taskQueue=new ArrayBlockingQueue<Runnable>(10);
	private Vector<PoolThread> workerThreads=new Vector<PoolThread>(10);
	private static final int POOL_SIZE=13;
	
	public ThreadPool() {
		for (int i = 0; i < POOL_SIZE; i++) {
			PoolThread workerThread=new PoolThread(taskQueue);
			workerThreads.add(workerThread);
			workerThread.start();
		}
		
	}
	
	public void shutDown(){
		if(!isShutDown){
		for (PoolThread thread : workerThreads) {
			thread.interrupt();
		}
		}
		isShutDown=true;
	}
	
	public void execute(Runnable task){
		while(!taskQueue.offer(task)){
			
		}
	}
	
	

}
