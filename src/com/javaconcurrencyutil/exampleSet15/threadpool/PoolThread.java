package com.javaconcurrencyutil.exampleSet15.threadpool;

import java.util.concurrent.BlockingQueue;

public class PoolThread extends Thread {
	private BlockingQueue<Runnable> taskQueue;
	private boolean isStopped;

	public PoolThread(BlockingQueue<Runnable> taskQueue) {
		this.taskQueue = taskQueue;
	}

	@Override
	public void run() {
		Runnable task = null;
		while (true) {

			try {
				task = taskQueue.take();
				task.run();
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}

		}
	}

	public void stopThread() {
		if (!isStopped) {
			this.interrupt();
		}
		this.isStopped = true;
	}

}
