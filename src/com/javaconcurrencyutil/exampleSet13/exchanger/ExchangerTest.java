/**
 * 
 */
package com.javaconcurrencyutil.exampleSet13.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @author Anuj Sharma Exchanger: A synchronization point at which threads can pair and
 *         swap elements within pairs. Each thread presents some object on entry
 *         to the exchange method, matches with a partner thread, and receives
 *         its partner's object on return. An Exchanger may be viewed as a
 *         bidirectional form of a SynchronousQueue. Exchangers may be useful in
 *         applications such as genetic algorithms and pipeline designs. Sample
 *         Usage: Here are the highlights of a class that uses an Exchanger to
 *         swap buffers between threads so that the thread filling the buffer
 *         gets a freshly emptied one when it needs it, handing off the filled
 *         one to the thread emptying the buffer.
 */
public class ExchangerTest {

	public static void main(String[] args) {
		Exchanger<Object> exchanger = new Exchanger<Object>();
		ExchangerTest test = new ExchangerTest();
		CoordinatingThreadOne threadOne = test.new CoordinatingThreadOne(
				exchanger, "Data of First Thread");
		CoordinatingThreadTwo threadTwo = test.new CoordinatingThreadTwo(
				exchanger, "Data of Second Thread");
		threadOne.start();
		threadTwo.start();
	}

	class CoordinatingThreadOne extends Thread {
		private Object exchangableObject;
		private Exchanger<Object> exchanger;

		public CoordinatingThreadOne(Exchanger<Object> exchanger,
				Object exchangeableObject) {
			this.exchangableObject = exchangeableObject;
			this.exchanger = exchanger;

		}

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println("ThreadOne is done with its data. Now it's time to exchange");
				exchangableObject = exchanger.exchange(exchangableObject);
				System.out.println("After exchanging ThreadOne received : "+ exchangableObject.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	class CoordinatingThreadTwo extends Thread {
		private Object exchangableObject;
		private Exchanger<Object> exchanger;

		public CoordinatingThreadTwo(Exchanger<Object> exchanger,Object exchangeableObject) {
			this.exchangableObject = exchangeableObject;
			this.exchanger = exchanger;

		}

		@Override
		public void run() {
			try {
				Thread.sleep(4000);
				System.out.println("ThreadTwo is done with its data. Now it's time to exchange");
				exchangableObject = exchanger.exchange(exchangableObject);
				System.out.println("After exchanging ThreadTwo received : "+ exchangableObject.toString());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
