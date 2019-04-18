package com.threadbasics.exampleSet4.interthreadcommunication;

import java.util.concurrent.atomic.AtomicInteger;

public class EvenPrintingThread extends Thread {
	private Object lock;
	private AtomicInteger counter;

	public EvenPrintingThread(Object lock,AtomicInteger counter2) {
		this.lock=lock;
		this.counter=counter2;
	}
	@Override
	public void run() {
		synchronized (lock) {
			while(counter.get() <10){
				if(counter.get() % 2 != 0){
					counter.set(counter.get()+1);
					System.out.print(counter+" ");
					lock.notify();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else{
					lock.notify();
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
