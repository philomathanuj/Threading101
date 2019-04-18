/**
 * 
 */
package com.threadbasics.exampleSet4.interthreadcommunication;

/**
 * @author Anuj Sharma
 *
 */
public class DependentTask extends Thread{
	/** Holds the status indicating if task is done*/
	private boolean isDone;
	/**This lock is shared between Main Thread and Dependent Task*/
	private Object sharedLock;
	
	public DependentTask(boolean isDone, Object lock) {
		this.isDone=isDone;
		this.sharedLock=lock;
	}
	@Override
	public void run() {
		synchronized (sharedLock) {
			System.out.println("Dependent Task is complete");
			isDone=true;
			sharedLock.notify();
		}
		
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	
	
}
