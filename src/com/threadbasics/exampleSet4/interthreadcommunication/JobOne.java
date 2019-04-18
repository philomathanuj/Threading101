/**
 * 
 */
package com.threadbasics.exampleSet4.interthreadcommunication;


/**
 * @author Anuj Sharma
 *
 */
public class JobOne extends Thread{
	private volatile boolean done;
	
	public JobOne(boolean done) {
		this.done=done;
	}
	@Override
	public void run() {
		synchronized (this) {
			System.out.println("Job One is complete");
			done=true;
		}
	}
	public boolean isDone() {
		return done;
	}

}
