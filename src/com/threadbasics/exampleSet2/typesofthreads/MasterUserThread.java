/**
 * 
 */
package com.threadbasics.exampleSet2.typesofthreads;

/**
 * @author Anuj Sharma
 *
 */
public class MasterUserThread extends Thread {
	
	@Override
	public void run() {
		System.out.println("I am going to call two more user threads");
		SubUserThreadOne subUserThreadOne=new SubUserThreadOne();
		SubUserThreadTwo subUserThreadTwo=new SubUserThreadTwo();
		subUserThreadOne.start();
		subUserThreadTwo.start();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("I am done calling two sub user threads now continuing with my flow");
	}

}
