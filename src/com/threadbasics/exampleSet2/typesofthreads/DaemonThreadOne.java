/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class DaemonThreadOne extends Thread{
@Override
public void run() {
	System.out.println("Inside Daemon Thread One");
}
}
