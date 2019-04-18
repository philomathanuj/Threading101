/**
 * 
 */
package com.javaconcurrencyutil.exampleSet10.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Anuj Sharma 
 * A synchronization aid that allows a set of threads to all
 *         wait for each other to reach a common barrier point. CyclicBarriers
 *         are useful in programs involving a fixed sized party of threads that
 *         must occasionally wait for each other. The barrier is called cyclic
 *         because it can be re-used after the waiting threads are released. A
 *         CyclicBarrier supports an optional Runnable command that is run once
 *         per barrier point, after the last thread in the party arrives, but
 *         before any threads are released. This barrier action is useful for
 *         updating shared-state before any of the parties continue.
 * 
 *         barrier.await(): Waits until all parties have invoked await on this
 *         barrier.
 *
 */
public class CyclicBarrierTest {
	
	public static void main(String[] args) {
		
		CyclicBarrierTest test=new CyclicBarrierTest();
		CyclicBarrier barrier=new CyclicBarrier(4,new Thread(){
			@Override
			public void run() {
				System.out.println("Barrier Point has reached");
			}
		});
		Thread[] workerThreads={test.new WorkerThreadOne(barrier), 
				test.new WorkerThreadTwo(barrier),
				test.new WorkerThreadThree(barrier)};
		
		for (int i = 0; i < workerThreads.length; i++) {
			workerThreads[i].start();
		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		barrier.reset();
		System.out.println("Exiting Main Thread");
		}
		
		public  class WorkerThreadOne extends Thread{
			CyclicBarrier barrier;
			public WorkerThreadOne(CyclicBarrier barrier) {
				this.barrier=barrier;
			}
			@Override
			public void run() {
				System.out.println("First Task is complete");
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
		
		public class WorkerThreadTwo extends Thread{
			CyclicBarrier barrier;
			public WorkerThreadTwo(CyclicBarrier barrier) {
				this.barrier=barrier;
			}
			@Override
			public void run() {
				System.out.println("Second Task is complete");
				try {
					barrier.await();		
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
		
		public class WorkerThreadThree extends Thread{
			CyclicBarrier barrier;
			public WorkerThreadThree(CyclicBarrier barrier) {
				this.barrier=barrier;
			}
			@Override
			public void run() {
				System.out.println("Third Task is complete");
				try {
					barrier.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		}
	}
