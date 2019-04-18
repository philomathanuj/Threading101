/**
 * 
 */
package com.threadbasics.exampleSet4.interthreadcommunication;

/**
 * @author Anuj Sharma
 * When wait method is called on a lock object, present thread releases the lock and will go into
 * a waiting state. It will keep on waiting until it receives a notification.
 * This program uses a shared lock object between main thread and dependent task.
 * Once dependent task is complete, it notifies main thread, which on notification wakes up, checks on the
 * condition isDone condition and resumes its operations.
 * <ul>
 * Please note following points with respect to wait and notify methods:
 * <li>wait and notify must always be called from within a synchronized context, while
 * wait and notify method update each other of the condition they are waiting to happen,
 * synchronized block guards the critical section. There is no point in calling wait and notify
 * when there is no lock or synchronization. Though compiler doesn't throw any exception
 * if you don't call them from a synchronized block, you will get a run time exception with the name:
 * IllegalMonitorStateException
 * </li>
 * <li>
 * wait and notify should always be called from within a loop to guard against spurious wake-ups.
 * </li>
 * <li>
 * Wait and notify should not use ThreadInstance as lock objects
 * </li>
 * <ul>
 *
 */
public class WaitAndNotifyTest2 {	
	public static void main(String[] args) {
		final Object lock=new Object();
		DependentTask task=new DependentTask(false, lock);
		task.start();
		synchronized (lock) {
			while(!task.isDone()){
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Dependent Task is completed and wait is over");
		}
		
		try {
			task.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Main Method Finished");
	}

}
