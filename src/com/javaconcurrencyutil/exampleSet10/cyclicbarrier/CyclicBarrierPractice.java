package com.javaconcurrencyutil.exampleSet10.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;



public class CyclicBarrierPractice {
	public static void main(String[] args){
		CyclicBarrierPractice test = new CyclicBarrierPractice();
		CyclicBarrier barrier = new CyclicBarrier(3,new Thread(){
			@Override
			public void run() {
				System.out.println(" Reached Barrier Point");
			}	
		});
		
		Thread [] workerThread = {test.new WorkerThreadOne(barrier),
				test.new WorkerThreadTwo(barrier) 	
		};
		
		for(int i=0;i<workerThread.length;i++){
			workerThread[i].start();
		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		} 
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		barrier.reset();
		System.out.println("Exiting Main Thread!");
	}
	
	public class WorkerThreadOne extends Thread{
		CyclicBarrier barrier;

		public WorkerThreadOne(CyclicBarrier barrier) {
			this.barrier = barrier;
		}

		@Override
		public void run() {
			System.out.println("Task One is complete");
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		
	}
	
	public class WorkerThreadTwo extends Thread{
		CyclicBarrier barrier;
		public WorkerThreadTwo(CyclicBarrier barrier) {
			this.barrier = barrier;
		}
		@Override
		public void run() {
			System.out.println("Task two is complete");
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

