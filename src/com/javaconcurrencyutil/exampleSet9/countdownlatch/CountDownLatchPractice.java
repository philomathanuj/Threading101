package com.javaconcurrencyutil.exampleSet9.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchPractice {


	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		CountDownLatchPractice test = new CountDownLatchPractice();
		Thread[] workerThread = { test.new WorkerThreadOne(latch),
				test.new WorkerThreadTwo(latch),
				test.new WorkerThreadThree(latch)};

		for(int i=0;i< workerThread.length;i++){
			workerThread[i].start();
		}
		
		try{
			latch.await();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Exiting Main Thread!!");
	}
	//Inner class 1
	public class WorkerThreadOne extends Thread{
		CountDownLatch latch;
		public WorkerThreadOne(CountDownLatch latch) {
			this.latch = latch;
		}
		@Override
		public void run() {
			System.out.println("Task one is complete!");
			latch.countDown();
		}
		
	}
	//Inner Class 2
	public class WorkerThreadTwo extends Thread{
		CountDownLatch latch;
		public WorkerThreadTwo(CountDownLatch latch) {
			this.latch = latch;
		}
		@Override
		public void run() {
			System.out.println("Task Two is Complete!");
			latch.countDown();
		}	
	}
	//Inner Class 3
	public class WorkerThreadThree extends Thread{
		CountDownLatch latch;

		public WorkerThreadThree(CountDownLatch latch) {
			this.latch = latch;
		}
		@Override
		public void run() {
		System.out.println("Task Three is complete!");
		latch.countDown();
		}
		
	}
}
