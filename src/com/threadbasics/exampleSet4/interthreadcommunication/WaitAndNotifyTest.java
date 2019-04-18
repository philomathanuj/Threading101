/**
 * 
 */
package com.threadbasics.exampleSet4.interthreadcommunication;

/**
 * @author Anuj Sharma
 * <b>This example illustrates that wait and notify mechanism should not use Thread instance
 * for locking. Because Thread instance internally uses wait and notify mechanism.
 * A Thread instance issues a notification when its run method is complete.
 * As a matter of fact, this is how join method works. When thread is finished with its task it issues
 * a notification and join method receives this notification to wake up the thread it is waiting upon.
 * </b>
 * In the below example, main thread is waiting upon JobOne to be completed inside a while loop
 * which checks for isDone condition. When program starts main method goes into a waiting state. When
 * Job One is done, it doesn't issue an explicit notification yet main method resumes.
 * Reason being JobOne thread internally issues a notification after its completion which wakes up main thread.
 * Now when main thread check the isDone condition, it comes out to be true, hence control comes out of the loop
 * and message saying Notification received is printed.
 * 
 *
 */
public class WaitAndNotifyTest {
	
	public static void main(String[] args) {
		JobOne jobOne=new JobOne(false);
		jobOne.start();
		// wait until job one has finished
		synchronized (jobOne) {
			try {
				while(!jobOne.isDone()){
				jobOne.wait();
				}
				System.out.println("Notification Received for completion of job one");
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		try {
			jobOne.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Interthread Communication is over. Exiting Main Thread");

		
		
		}
	}


