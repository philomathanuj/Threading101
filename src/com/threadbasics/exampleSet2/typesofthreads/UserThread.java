/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;

/**
 * @author Anuj Sharma
 * Daemon Threads cannot outlive user threads
 * User Threads wait for all the daemon threads to complete
 *
 */
public class UserThread extends Thread{
	
	@Override
	public void run() {
		System.out.println("Hello I am a user thread");
		System.out.println("I am going to create two daemon threads");
		DaemonThreadOne d1=new DaemonThreadOne();
		d1.setDaemon(true);
		DaemonThreadTwo d2=new DaemonThreadTwo();
		d2.setDaemon(true);
		d1.start();
		d2.start();
	}

}
