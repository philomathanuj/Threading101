package com.javaconcurrencyutil.exampleSet19.blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
	private BlockingQueue bq= null;

	public Producer(BlockingQueue queue) {
		this.setBlockingQueue(queue);
	}

	private void setBlockingQueue(BlockingQueue bq) {
		this.bq = bq;	
	}

	public void run() {
		Random rand = new Random();
		int res =0;
		try{
			res = Addition(rand.nextInt(50), rand.nextInt(50));
			System.out.println("Produced: " + res);
			bq.put(res);
			Thread.sleep(1000);
			res = Addition(rand.nextInt(50), rand.nextInt(50));
			System.out.println("Produced: " + res);
			bq.put(res);
			Thread.sleep(1000);
			res = Addition(rand.nextInt(50), rand.nextInt(50));
			System.out.println("Produced: " + res);
			bq.put(res);

		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	public int Addition(int x, int y) {
		int result =0;
		System.out.println("x="+x + " y= "+y);
		result =x+y;
		return result;
	}

}
