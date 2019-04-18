package com.threadbasics.exampleSet2.typesofthreads;


/**
 * User Threads don't wait for other user threads to complete
 * @author Anuj Sharma
 *
 */
public class ThreadTypesTest2 {
	public static void main(String[] args) {
		MasterUserThread masterUserThread=new MasterUserThread();
		masterUserThread.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Master Thread Running Status : "+masterUserThread.isAlive());
	}
}
